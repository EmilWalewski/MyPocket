import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { AuthService } from './services/auth/auth.service';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { ExampleComponent } from './components/trol';
import { HomeComponent } from './components/home/home.component';
import { WelcomeGuard } from './guards/welcome/welcome.guard';
import { AuthGuard } from './guards/auth/auth.guard';
import { ReceiptDatePipe } from './pipes/receipt-date.pipe';
import { AuthInterceptorService } from './services/interceptors/auth-interceptor/auth-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    WelcomePageComponent,
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
  providers: [AuthService, AuthGuard, WelcomeGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
