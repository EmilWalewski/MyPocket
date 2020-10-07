import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, filter, map, mapTo, publishReplay, refCount, switchMap, take, tap } from 'rxjs/operators';
import { of } from 'rxjs/internal/observable/of';
import { ApiResponse } from 'src/app/models/serverResponse/api-response';
import { Observable, throwError } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class WalletService {

  private API_URL = 'http://localhost:8080';

  private cashedWallets$: Observable<Array<WalletResponse>>;

  constructor(private httpService: HttpClient, private authService: AuthService) { }

  getWallets(): Observable<Array<WalletResponse>> {

    if (this.cashedWallets$ === undefined) {
      console.log('cash array = 0');
      return this.authService.cashedUser
        .pipe(
          switchMap(user => {
            const options = {
              withCredentials: false,
              headers: {
                'E-SEL-MAR-XX': user
              }
            };

            this.cashedWallets$ = this.httpService.get<Array<WalletResponse>>(`${this.API_URL}/wallet/`, options)
              .pipe(
                publishReplay(1),
                refCount()
              );
            return this.cashedWallets$;
          })
        );
    }

    return this.cashedWallets$;
  }

  getWallet(index: number): Observable<WalletResponse> {
    return this.getWallets()
      .pipe(
        map(array => {
          console.log(array[index]);
          return array[index];
        }
        )
      );
  }

  getMainWallet(receipt) {
    return this.getWallets()
      .pipe(
        map(array =>
          array.filter(wallet => wallet.isMainWallet === true)
        ),
        map(array => array[0]),
        tap(wallet => wallet.quantity = wallet.quantity - +receipt.price),
      );
  }

  updateCashedWallets(wallets) {
    this.cashedWallets$
      .pipe(
        tap(walletsList =>
          walletsList = wallets
        )
      );
  }

  uploadWallet(wallet: Wallet) {

    return this.authService.cashedUser
      .pipe(
        switchMap(credentials => {

          return this.httpService.post<any>(`${this.API_URL}/wallet/`, wallet,
            {
              headers: {
                'E-SEL-MAR-XX': credentials
              },
              observe: 'response'
            });
        }),
        mapTo(true),
        tap(someData => this.cashedWallets$ = undefined),
        catchError(error => {
          return throwError(error);
        })
      );
  }

  saveAllChanges(walletList) {

    return this.authService.cashedUser
      .pipe(
        switchMap(credentials => {

          return this.httpService.post<any>(`${this.API_URL}/wallet/list`, walletList,
            {
              headers: {
                'E-SEL-MAR-XX': credentials
              },
              observe: 'response'
            });

        })
      ).subscribe(data => { });
  }
}

export interface WalletResponse {

  id?: number;
  name: string;
  quantity: number;
  isMainWallet: boolean;

}

export interface Wallet {

  name: string;
  quantity: number;
  isMainWallet: boolean;
}
