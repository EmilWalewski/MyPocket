import { Component, Output, EventEmitter, ViewChild, ViewContainerRef, AfterViewInit, TemplateRef, ComponentFactoryResolver } from '@angular/core';
import { ObjectUnsubscribedError, of } from 'rxjs';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { AuthService } from './services/auth/auth.service';
import { filter, mapTo, take } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit {

  @ViewChild('welcomePage', { read: ViewContainerRef }) welcomeVcr: ViewContainerRef;

  // used in mobile view
  open = false;

  // open login dialog
  openLoginMenu = false;

  currentUser;

  constructor(private factory: ComponentFactoryResolver, private auth: AuthService) {

    // this.auth.currentUserV.subscribe(x => this.currentUser = x);

    // const a = 2;

    // of(1, 2, 3, 4, 5, 6, 7, 8).pipe(filter(val => (val % 2) === 0),
    //   mapTo(true),
    //   take(1)
    // ).subscribe(v => console.log(v));

    // console.log(this.fun);

  }

  logout() {
    console.log('log out');
    this.auth.logout();
  }


  ngAfterViewInit(): void {

    // const factor = this.factory.resolveComponentFactory(WelcomePageComponent);
    // this.welcomeVcr.createComponent(factor);
  }

  switchMenu() {
    this.open = !this.open;
  }

  openMenu() {
    this.openLoginMenu = !this.openLoginMenu;
    this.open = false;
  }

  closeMenu() {
    this.openLoginMenu = false;
  }
}
