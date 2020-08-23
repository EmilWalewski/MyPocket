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

  constructor(private httpService: HttpClient) {
    this.logged$ = new BehaviorSubject<boolean>(false);
    this.currentUserV = this.logged$.asObservable();
  }

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
  }

  public get currentUser() {
    return !!localStorage.getItem('auth_token');
  }

  public logout() {
    localStorage.removeItem('auth_token');
    this.logged$.next(null);
  }

}
