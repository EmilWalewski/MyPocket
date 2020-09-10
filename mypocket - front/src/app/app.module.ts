import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
<<<<<<< HEAD
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { AuthService } from './services/auth/auth.service';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
=======
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { AuthService } from './services/auth/auth.service';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { ComponentsUnwrapperDirective } from './directives/components-unwrapper.directive';
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
import { ExampleComponent } from './components/trol';
import { HomeComponent } from './components/home/home.component';
import { WelcomeGuard } from './guards/welcome/welcome.guard';
import { AuthGuard } from './guards/auth/auth.guard';
import { ReceiptDatePipe } from './pipes/receipt-date.pipe';
<<<<<<< HEAD
import { AuthInterceptorService } from './services/interceptors/auth-interceptor/auth-interceptor.service';
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    WelcomePageComponent,
<<<<<<< HEAD
=======
    ComponentsUnwrapperDirective,
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
    ExampleComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
<<<<<<< HEAD
  providers: [AuthService, AuthGuard, WelcomeGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
=======
  providers: [AuthService, AuthGuard, WelcomeGuard],
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
  bootstrap: [AppComponent],
})
export class AppModule { }
