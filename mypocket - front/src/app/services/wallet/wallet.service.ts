import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, mapTo } from 'rxjs/operators';
import { of } from 'rxjs/internal/observable/of';
import { ApiResponse } from 'src/app/models/serverResponse/api-response';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WalletService {

  private apiUrl = 'http://localhost:8080';

  constructor(private httpService: HttpClient) { }


  uploadWallet(wallet: Wallet){
    return this.httpService.post<any>(`${this.apiUrl}/wallet/`, wallet)
    .pipe(
      mapTo(true),
      catchError(error => {
        return throwError(error.error);
      })
    );
  }
}

export interface Wallet {

  name: string;
  quantity: number;
}
