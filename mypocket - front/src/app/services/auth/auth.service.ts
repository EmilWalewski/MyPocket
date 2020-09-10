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

<<<<<<< HEAD
  private apiUrl = 'http://localhost:8080';

=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
  constructor(private httpService: HttpClient) {
    this.logged$ = new BehaviorSubject<boolean>(false);
    this.currentUserV = this.logged$.asObservable();
  }

<<<<<<< HEAD
  authenticate(userData: LoginData) {

    return this.httpService.post<ApiResponse>(`${this.apiUrl}/authenticate/`, userData)
      .pipe(
        tap(res => {
          localStorage.setItem('auth_token', res.token);
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
=======
  authenticate(userData: LoginData): Observable<boolean> {

    return this.httpService.post<ApiResponse>('http://localhost:8080/authenticate/', userData)
      .pipe(
        switchMap(res => {
          localStorage.setItem('auth_token', res.token);

          // stay logged in throw pages refresh
          this.logged$.next(true);

          // return 0 validation errors
          return of(false);
        }),
      );
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
  }

  public get currentUser() {
    return !!localStorage.getItem('auth_token');
  }

  public logout() {
    localStorage.removeItem('auth_token');
    this.logged$.next(null);
  }

}
