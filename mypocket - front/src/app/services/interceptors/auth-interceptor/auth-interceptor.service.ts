import { Injectable, Injector } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { AuthService } from '../../auth/auth.service';
import { catchError, switchMap, filter, take } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  private authService: AuthService;
  private refreshingToken$ = new BehaviorSubject<any>(null);
  private isRefreshing = false;

  constructor(private injector: Injector) {
    this.authService = this.injector.get(AuthService);
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    // req = req.clone({
    //   headers: req.headers.set('Content-Type', 'application/json')
    // });

    req = this.addToken(req);

    return next.handle(req)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 401) {
            return this.handleUnauthorizedRequest(req, next);
          }
          else {
            return throwError(error);
          }
        })
      );
  }

  private handleUnauthorizedRequest(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (!this.isRefreshing) {

      this.isRefreshing = true;
      this.refreshingToken$.next(null);

      return this.authService.refreshToken()
        .pipe(
          switchMap(response => {
            this.isRefreshing = false;
            this.refreshingToken$.next(response.token);
            return next.handle(this.addToken(req));
          })
        );
    }
    else {

      return this.refreshingToken$
        .pipe(
          filter(token => token !== null),
          take(1),
          switchMap(token => {
            return next.handle(this.addToken(req));
          })
        );
    }

    return next.handle(req);
  }

  addToken(req: HttpRequest<any>): HttpRequest<any> {

    if (!this.authService.currentUser) {
      return req;
    }

    return req.clone({
      headers: req.headers.set('Authorization', `Bearer ${this.authService.getToken()}`)
    });
  }

}
