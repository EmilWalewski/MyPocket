import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { LoginData, LoginRequest } from '../../models/loginModel/login-request';
import { map, tap, catchError, switchMap, filter, mapTo, take, publishReplay, refCount } from 'rxjs/operators';
import { ApiResponse } from '../../models/serverResponse/api-response';
import { Observable, Subject, BehaviorSubject, of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  res: ApiResponse;

  private logged$;
  public currentUserV;

  private API_URL = 'http://localhost:8080';

  private cachedUser$: Observable<any>;

  private globalHeader = new HttpHeaders().set('Content-Type', 'application/json');


  constructor(private httpService: HttpClient) {
    this.logged$ = new BehaviorSubject<boolean>(false);
    this.currentUserV = this.logged$.asObservable();
  }



  authenticate(userData: LoginData) {

    const httpOptions = {
      withCredentials: false, // if you want use cookie set to true
      observe: 'response' as 'response'
    };

    return this.httpService.post<ApiResponse>(`${this.API_URL}/authenticate/`, userData, httpOptions)
      .pipe(
        tap(res => {
          localStorage.setItem('auth_token', res.body.token);
          this.cachedUser$ = this.cashedUser;
        }),
        mapTo(true),
        catchError(error => {
          return of(error);
        })
      );
  }

  public get cashedUser() {

    if (this.cachedUser$ === undefined) {
      this.cachedUser$ = this.httpService.post<any>(`${this.API_URL}/user/`, localStorage.getItem('auth_token')
        , { headers: this.globalHeader })
        .pipe(
          publishReplay(1),
          refCount()
        );
    }

    return this.cachedUser$;
  }

  public refreshToken(): Observable<ApiResponse> {

    return this.httpService.post<ApiResponse>(`${this.API_URL}/authenticate/refresh`, { refreshToken: this.refreshToken() })
      .pipe(tap(response => {
        this.storeJwtToken(response.token);
      }));
  }

  private storeJwtToken(token: string) {
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
