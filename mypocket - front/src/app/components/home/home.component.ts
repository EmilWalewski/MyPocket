import { Component, OnInit, ViewChild, ElementRef, ViewContainerRef, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import * as Tesseract from 'tesseract.js';
import { createWorker } from 'tesseract.js';
import { Dictionary, OCRResponse } from 'src/app/dictionary/dictionary';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { ReceiptService } from 'src/app/services/receipt/receipt.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  @ViewChild('progressContainer', { read: ViewContainerRef }) progressContainer: ViewContainerRef;
  @ViewChild('processingBar') progressTemplate: TemplateRef<any>;

  @ViewChild('fileSelector') fileInput: ElementRef;

  private worker;
  private dictionary = new Dictionary();
  progress = 1;
  // private recipe: OCRResponse;

  private tesseractConfig = {
    // If you want to set the language explicitly:
    lang: 'pol',
    // You can play around with half-documented options:
    tessedit_char_whitelist: ' 0123456789',
  };


  ocrResult;
  private filename = './assets/images/abc.jpg';


  constructor(private router: Router, private route: ActivatedRoute, private receiptService: ReceiptService) {

    this.doOCR();

  }
  async doOCR() {

    this.worker = createWorker({
      workerPath: '/assets/lib/tesseract.js/dist/worker.min.js',
      corePath: '/assets/lib/tesseract.js-core/tesseract-core.wasm.js',
      // langPath: '/assets/lib/lang/tesseract.js-',
      logger: m => this.progress = m.progress,
    });
  }

  async onFileChange(event) {

    // this.progressContainer.createEmbeddedView(this.progressTemplate);

    await this.worker.load();
    await this.worker.loadLanguage('pol');
    await this.worker.initialize('pol');
    // const { data: { lines } } = await this.worker.recognize(event.target.files[0], this.tesseractConfig);
    // .progress((v) => {
    // v.status is a textual string of what Tesseract is doing
    // v.progress is a 0 - 1 decimal representation of the progress.
    // The progress resets for each new v.status,
    // but the major event is v.status == "recognizing text".
    // console.log(v.status, status.progress);
    // })
    // .catch((err) => {
    //   console.error('OcrProvider: Failed to analyze text.', err);
    // })
    // .then((result) => {
    //   console.log(result);


    // Result contains these elements:
    // blocks: Array
    // confidence: 0 - 100
    // html: string
    // lines: string[]
    // oem: "DEFAULT"
    // paragraphs: string[]
    // psm: "SINGLE_BLOCK"
    // symbols: Array
    // text: string
    // version: "3.04.00"
    // words: string[]
    // I chose to use a regex to find the
    // correct format out of result.text.
    // });

    const recipe = { date: '', time: '', price: '' };


    // this.ocrResult = lines;
    // this.ocrResult.forEach(element => {
    //   element.words.forEach(word => {

    //     // console.log(word);

    //     if (this.dictionary.dateParser(word.text)) {
    //       recipe.date = word.text;
    //     }

    //     if (this.dictionary.timeParser(word.text)) {
    //       recipe.time = word.text;
    //     }

    //     if (this.dictionary.priceParser(word.text)) {
    //       recipe.price = word.text;
    //     }

    //   });
    // });

    await this.worker.terminate();

    console.log(recipe);

    // this.receiptService.uploadImage(event.target.files[0]);

    this.progressContainer.clear();
  }

  ngOnInit(): void {
  }

  getShoppingList() {
    this.router.navigate(['list'], { relativeTo: this.route });
  }

  getAccountBalance() {
    this.router.navigate(['account'], { relativeTo: this.route });
  }

}
