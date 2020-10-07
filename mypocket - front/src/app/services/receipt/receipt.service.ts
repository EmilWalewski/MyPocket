import { Injectable, OnDestroy } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, switchMap, map, catchError, shareReplay, refCount, filter, publishReplay, take } from 'rxjs/operators';
import { Time } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {

  private API_URL = 'http://localhost:8080';

  constructor(private httpService: HttpClient, private router: Router, private route: ActivatedRoute
    , private authService: AuthService) {
    // this.router.onSameUrlNavigation = 'reload';
  }

  private cachedReceiptArray$: Observable<Array<ReceiptResponse>>;

  uploadReceipt(receipt, event?): Observable<any> {

    const formData = new FormData();
    const t = { id: '', shop_name: '', shopping_date: '', price: '' };

    if (event != null) {

      // UPLOAD NEW RECEIPT

      formData.append('file', event);


      t.shop_name = receipt.shop_name;
      t.shopping_date = receipt.date + ' ' + receipt.time;
      t.price = receipt.price;

      formData.append('receipt_data', JSON.stringify(t));

    }
    else {

      // UPDATE RECEIPT DATE

      formData.append('receipt_data', JSON.stringify(receipt));

      this.updateCashedReceiptArray(receipt);
    }

    // get cashed user Credentials

    return this.authService.cashedUser
      .pipe(
        switchMap(credentials => {

          return this.httpService.post<any>(`${this.API_URL}/receipt/`, formData,
            {
              headers: {
                'E-SEL-MAR-XX': credentials
              },
              observe: 'response'
            });

        })
      );

  }

  updateCashedReceiptArray(receipt) {

    this.cachedReceiptArray$.
      pipe(
        tap(array =>
          array.forEach(re => {
            if (re.id === receipt.id) {
              re = receipt;
            }
          })
        )
      );
  }

  getReceipt(id: number): Observable<any> {

    if (this.cachedReceiptArray$ === undefined) {
      console.log('odswiezam po updacie');
      this.cachedReceiptArray$ = this.getReceipts();
    }

    return this.cachedReceiptArray$.pipe(
      map((array: Array<ReceiptResponse>) => array.filter(receipt => receipt.id === id)),
      map(oneValueArray => oneValueArray[0])
    );

  }

  getReceipts(): Observable<Array<ReceiptResponse>> {

    if (this.cachedReceiptArray$ === undefined) {
      console.log('cash array = 0');
      return this.authService.cashedUser
        .pipe(
          switchMap(user => {
            const options = {
              withCredentials: true,
              headers: {
                'E-SEL-MAR-XX': user
              }
            };

            this.cachedReceiptArray$ = this.httpService.get<Array<ReceiptResponse>>(`${this.API_URL}/receipt/`, options)
              .pipe(
                publishReplay(1),
                refCount()
              );
            return this.cachedReceiptArray$;
          })
        );
    }

    return this.cachedReceiptArray$;
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
