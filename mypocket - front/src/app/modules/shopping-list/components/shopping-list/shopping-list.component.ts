import { Component, OnInit, ViewChild, ElementRef, ViewContainerRef, TemplateRef, AfterViewInit, ViewChildren, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, ActivatedRouteSnapshot } from '@angular/router';
import { Dictionary } from 'src/app/dictionary/dictionary';
import { createWorker } from 'tesseract.js';
import { ReceiptService, ReceiptResponse } from 'src/app/services/receipt/receipt.service';
import { toBase64String } from '@angular/compiler/src/output/source_map';
import { DomSanitizer } from '@angular/platform-browser';
import { tap, switchMap } from 'rxjs/operators';
import { Subscription, Observable, of } from 'rxjs';
import { Type } from '@angular/compiler';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.scss']
})
export class ShoppingListComponent implements OnInit, OnDestroy {

  /**
   *    File Selector Dialog View Child
   */
  @ViewChild('fileSelector') fileDialog: ElementRef;

  /**
   *    Processing Controls View Children
   */
  @ViewChild('progressContainer', { read: ViewContainerRef }) progressContainer: ViewContainerRef;
  @ViewChild('processingBar') progressTemplate: TemplateRef<any>;


  // @ViewChild('receiptPhoto') photo: ElementRef;

  /**
   *    Data Selector View Child
   */
  @ViewChild('dateSelector') dateSelector: ElementRef;


  /**
   *    Read text from image controls
   */
  private worker;
  private dictionary = new Dictionary();
  progress = 1;

  /**
   *    Receipt list and help table
   */
  receiptLists: ReceiptResponse[];
  private tempTable: ReceiptResponse[] = new Array();

  // check if whatever date is selected
  indicatorIsVisible = false;


  private tesseractConfig = {
    // If you want to set the language explicitly:
    lang: 'pol',
    // You can play around with half-documented options:
    tessedit_char_whitelist: ' 0123456789',
  };

  constructor(private router: Router, private route: ActivatedRoute,
    private receiptService: ReceiptService, private domSanitizer: DomSanitizer) {

    this.doOCR();

  }

  ngOnInit(): void {
    this.getReceipts();
  }

  getReceipts() {
    this.receiptService.getReceipts()
      .subscribe(receiptLists => this.receiptLists = receiptLists);
  }

  ngOnDestroy(): void {
    // this.uploadSubscription.unsubscribe();
  }

  async doOCR() {

    this.worker = createWorker({
      workerPath: '/assets/lib/tesseract.js/dist/worker.min.js',
      corePath: '/assets/lib/tesseract.js-core/tesseract-core.wasm.js',
      // langPath: '/assets/lib/lang/tesseract.js-',
      logger: m => this.progress = m.progress,
    });
  }

  openFileDialog() {
    this.fileDialog.nativeElement.click();
  }

  async onFileChange(event) {

    if (event.target.files != null) {

      this.progressContainer.createEmbeddedView(this.progressTemplate);

      await this.worker.load();
      await this.worker.loadLanguage('pol');
      await this.worker.initialize('pol');
      const { data: { lines } } = await this.worker.recognize(event.target.files[0], this.tesseractConfig);

      const receipt = { id: '', shop_name: '', date: '', time: '', price: '' };


      // this.ocrResult = lines;
      lines.forEach(element => {
        element.words.forEach(word => {

          // console.log(word.text);

          if (this.dictionary.recognizeShop(word.text)) {
            receipt.shop_name = this.dictionary.getRecognizedShopName(word.text);
          }

          if (this.dictionary.dateParser(word.text)) {
            receipt.date = word.text;
          }

          if (this.dictionary.timeParser(word.text)) {
            receipt.time = word.text;
          }

          if (this.dictionary.priceParser(word.text)) {
            receipt.price = word.text;
          }

        });
      });

      await this.worker.terminate();

      console.log(receipt);

      this.receiptService
        .uploadReceipt(receipt, event.target.files[0])
        .subscribe(data => {
          this.router.navigate([data.body.id], { relativeTo: this.route });
        });

      this.progressContainer.clear();

      this.fileDialog.nativeElement.value = '';

      this.progress = 0;


    } // end if

  }

  filterReceipts(event) {

    if (event instanceof Object) {

      this.tempTable.length > 0 ? this.receiptLists = this.tempTable : undefined;

      if (event.target.value !== '') {

        this.tempTable = this.receiptLists;
        this.receiptLists =
          this.receiptLists
            .filter(r =>
              new Date(r.shopping_date).getMonth() === (new Date(event.target.value).getMonth() + 1)
            );
        this.indicatorIsVisible = true;

      } else {
        this.receiptLists = this.tempTable;
      }

    } else {
      this.receiptLists = this.tempTable;
      this.indicatorIsVisible = false;
      this.dateSelector.nativeElement.value = null;
    }
  }

  setMargin() {
    return this.indicatorIsVisible === false ?
      { marginBottom: '3rem' } :
      { marginBottom: '2rem' };
  }

  closeFilter() {
    this.filterReceipts('');
  }

  showReceiptDetails(id: number) {
    // this.router.navigate([{ outlets: { primary: '', bolek: [id] } }]);
    this.router.navigate([id], { relativeTo: this.route });

    //   this.router.routeReuseStrategy.shouldReuseRoute = (future: ActivatedRouteSnapshot,
    //     curr: ActivatedRouteSnapshot) => {
    //     if (future.url.toString() === 'view' && curr.url.toString() === future.url.toString()) {
    //       return false;
    //     }
    //     return (future.routeConfig === curr.routeConfig);
    //   // console.log(id);
    // }
  }

  goToShop() {
    this.router.navigate(['shop'], { relativeTo: this.route });
  }

  getDate(event) {
    console.log(event.target.value);
  }

  goToTest1() {
    this.router.navigate(['shop'], { relativeTo: this.route });
  }

}
