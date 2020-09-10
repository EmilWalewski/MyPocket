import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
<<<<<<< HEAD
import { tap, switchMap, map, catchError } from 'rxjs/operators';
=======
import { tap, switchMap, map } from 'rxjs/operators';
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
import { Time } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {

  constructor(private httpService: HttpClient, private router: Router, private route: ActivatedRoute) {
    // this.router.onSameUrlNavigation = 'reload';
  }


<<<<<<< HEAD
  uploadReceipt(receipt, event?): Observable<HttpResponse<any>> {
=======
  uploadReceipt(receipt, event?): Observable<HttpResponse<ApiResponse>> {
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59

    const formData = new FormData();

    if (event != null) {
<<<<<<< HEAD

      console.log(event);
      formData.append('file', event);

      const t = { id: '', shop_name: '', shopping_date: '', price: '' };

      t.shop_name = receipt.shop_name;
      t.shopping_date = receipt.date + ' ' + receipt.time;
      t.price = receipt.price;

      formData.append('receipt_data', JSON.stringify(t));

      console.log(t);
    }
    else {
      formData.append('receipt_data', JSON.stringify(receipt));
      // return this.httpService.post<ApiResponse>('http://localhost:8080/receipt/', receipt, { observe: 'response' });
    }

    return this.httpService.post<any>('http://localhost:8080/receipt/', formData, { observe: 'response' });

=======
      formData.append('file', event);
      const t = { id: '', shop_name: '', shopping_date: '', price: '' };
      t.shop_name = receipt.shop_name;
      t.shopping_date = receipt.date + ' ' + receipt.time;
      t.price = receipt.price;
      formData.append('receipt_data', JSON.stringify(t));
    }
    else {
      formData.append('receipt_data', JSON.stringify(receipt));
    }

    return this.httpService.post<ApiResponse>('http://localhost:8080/receipt/', formData, { observe: 'response' });
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
  }

  getReceipt(id: number): Observable<ReceiptResponse> {

    return this.httpService.get<ReceiptResponse>('http://localhost:8080/receipt/' + id);

  }

  getReceipts(): Observable<Array<ReceiptResponse>> {
    return this.httpService.get<Array<ReceiptResponse>>('http://localhost:8080/receipt/');
  }
}

export interface ApiResponse {
  id: number;
  httpStatus: string;
}

export interface ReceiptResponse {
  id: number;
  shop_name: string;
  price: number;
  shopping_date: string;
  // receipt_photo: ReceiptImage;
  encodedImage: string;
}

interface ReceiptImage {
  id: number;
  fileName: string;
  fileType: string;
  data: string;
}
