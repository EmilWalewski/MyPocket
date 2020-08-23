import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../../services/auth/auth.service';
import { take, filter, mapTo } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    // console.log('is logged' + this.authService.currentUser);

    // if (this.authService.currentUser.pipe(filter(token => !!token), mapTo(true), take(1))) {

    if (this.authService.currentUser) {
      return true;
    }

    console.log(state.url);
    this.router.navigate(['/'], {
      queryParams: {
        return: state.url
      }
    });
    return false;

  }

}
