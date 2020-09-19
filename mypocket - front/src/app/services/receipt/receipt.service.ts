import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, switchMap, map, catchError } from 'rxjs/operators';
import { Time } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {

  private API_URL = 'http://localhost:8080';

  constructor(private httpService: HttpClient, private router: Router, private route: ActivatedRoute) {
    // this.router.onSameUrlNavigation = 'reload';
  }


  uploadReceipt(receipt, event?): Observable<HttpResponse<any>> {

    const formData = new FormData();
    const t = { id: '', shop_name: '', shopping_date: '', price: '', userName: '' };
    const shakeHead = new HttpHeaders().set('Content-Type', 'application/json');

    if (event != null) {

      console.log(event);
      formData.append('file', event);


      t.shop_name = receipt.shop_name;
      t.shopping_date = receipt.date + ' ' + receipt.time;
      t.price = receipt.price;

      // formData.append('receipt_data', JSON.stringify(t));

    }
    else {

      return this.httpService.post<any>(`${this.API_URL}/user/`, localStorage.getItem('auth_token'), { headers: shakeHead })
        .pipe(
          switchMap(userData => {
            receipt.userName = userData;
            console.log('here we are: ' + JSON.stringify(receipt));
            formData.append('receipt_data', JSON.stringify(receipt));
            return this.httpService.post<any>(`${this.API_URL}/receipt/`, formData, { observe: 'response' });
          })
        );
    }

    return this.httpService.post<any>(`${this.API_URL}/user/`, localStorage.getItem('auth_token'), { headers: shakeHead })
      .pipe(
        switchMap(userData => {
          t.userName = userData;
          formData.append('receipt_data', JSON.stringify(t));
          return this.httpService.post<any>(`${this.API_URL}/receipt/`, formData, { observe: 'response' });
        })
      );
    // return this.httpService.post<any>(`${this.API_URL}/receipt/`, formData, { observe: 'response' });

  }

  getReceipt(id: number): Observable<ReceiptResponse> {

    return this.httpService.get<ReceiptResponse>('http://localhost:8080/receipt/' + id);

  }

  getReceipts(): Observable<Array<ReceiptResponse>> {
    const usCredHeader = new HttpHeaders();
    const shakeHead = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpService.post<any>(`${this.API_URL}/user/`, localStorage.getItem('auth_token'), { headers: shakeHead })
      .pipe(
        switchMap(userData => {
          let k = usCredHeader.set('Pot', userData);
          return this.httpService.get<Array<ReceiptResponse>>(`${this.API_URL}/receipt/`, { headers: k });
        })
      );
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
