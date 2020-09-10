import { Component, OnInit, AfterViewInit, OnDestroy, ViewChild, TemplateRef, ViewContainerRef } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { ReceiptService, ReceiptResponse, ApiResponse } from 'src/app/services/receipt/receipt.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Observable, Subscription } from 'rxjs';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-shopping-details',
  templateUrl: './shopping-details.component.html',
  styleUrls: ['./shopping-details.component.scss']
})
export class ShoppingDetailsComponent implements OnInit, AfterViewInit, OnDestroy {

  receipt$: Subscription;
  receipt: ReceiptResponse;
  url;
  dateValue;
  showModal;

  @ViewChild('modal') modal: TemplateRef<any>;
  @ViewChild('container', { read: ViewContainerRef }) container: ViewContainerRef;


  constructor(private receiptService: ReceiptService, private route: ActivatedRoute, private sanitizer: DomSanitizer,
              private router: Router) {

    // prevent before using current component, create new each time
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.getDetails();
  }

  getDetails() {

    // console.log(this.route.snapshot.paramMap.get('id'));

    this.receipt$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) => {
        console.log(params.get('id'));
        return this.receiptService.getReceipt(+params.get('id'));
      })
    ).subscribe(data => {
      this.receipt = data;
      // console.log(data);
      this.url = this.sanitizer.bypassSecurityTrustUrl('data:image/jpg;base64,' + data.encodedImage);
      this.dateValue = this.receipt.shopping_date;
    });

    // this.receiptService.getReceipt(id)
    // .subscribe(data => {
    //     this.receipt = data;
    //     // console.log(data);
    //     this.url = this.sanitizer.bypassSecurityTrustUrl('data:image/jpg;base64,' + data.encodedImage);
    //     this.dateValue = this.receipt.shopping_date;
    //   });
  }

  ngOnInit(): void {

  }

  setNewShoppingDate(event) {

    let receiptDate = new Date(this.receipt.shopping_date);

    const hours = receiptDate.getHours();
    const minutes = receiptDate.getMinutes();

    receiptDate = new Date(event.target.value);
    receiptDate.setHours(hours);
    receiptDate.setMinutes(minutes);

    const b = new DatePipe('en-US').transform(receiptDate, 'yyyy-MM-dd HH:mm');

    console.log(b);
    this.receipt.shopping_date = b;

    console.log(this.receipt);
    this.receiptService.uploadReceipt(this.receipt)
      .subscribe(response => {

        if (response.body.httpStatus === 'OK') {

          this.showModal = true;
          setTimeout(() => {
            this.showModal = false;
          }, 2000);

        }
      });

  }

  ngAfterViewInit(): void {

  }

  ngOnDestroy(): void {
    console.log('niszcze komponent');
    this.receipt$.unsubscribe();
  }

}
