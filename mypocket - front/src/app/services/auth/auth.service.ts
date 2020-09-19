import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { LoginData, LoginRequest } from '../../models/loginModel/login-request';
import { map, tap, catchError, switchMap, filter, mapTo, take } from 'rxjs/operators';
import { ApiResponse } from '../../models/serverResponse/api-response';
import { Observable, Subject, BehaviorSubject, of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  res: ApiResponse;

  private logged$;
  public currentUserV;

  private apiUrl = 'http://localhost:8080';

  constructor(private httpService: HttpClient) {
    this.logged$ = new BehaviorSubject<boolean>(false);
    this.currentUserV = this.logged$.asObservable();
  }

  authenticate(userData: LoginData) {

    const httpOptions = {
      // withCredentials: true,
      observe: 'response' as 'response'
    };

    return this.httpService.post<ApiResponse>(`${this.apiUrl}/authenticate/`, userData, httpOptions)
      .pipe(
        tap(res => {
          localStorage.setItem('auth_token', res.body.token);
          // console.log(res.headers.keys());
          // console.log(document.cookie.split(';'));
        }),
        mapTo(true),
        catchError(error => {
          return of(error);
        })
      );
  }

  public refreshToken(): Observable<ApiResponse> {

    return this.httpService.post<ApiResponse>(`${this.apiUrl}/authenticate/refresh`, { refreshToken: this.refreshToken() })
    .pipe(tap(response => {
      this.storeJwtToken(response.token);
    }));
  }

  private storeJwtToken(token: string){
    localStorage.setItem('auth_token', token);
  }

  public getToken() {
    return localStorage.getItem('auth_token');
  }

  private getRefreshToken() {
    return localStorage.getItem('refresh_token');
  }

  public get currentUser() {
    return !!localStorage.getItem('auth_token');
  }

  public logout() {
    localStorage.removeItem('auth_token');
    this.logged$.next(null);
  }

}
