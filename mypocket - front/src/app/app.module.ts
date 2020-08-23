import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { AuthService } from './services/auth/auth.service';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { ComponentsUnwrapperDirective } from './directives/components-unwrapper.directive';
import { ExampleComponent } from './components/trol';
import { HomeComponent } from './components/home/home.component';
import { WelcomeGuard } from './guards/welcome/welcome.guard';
import { AuthGuard } from './guards/auth/auth.guard';
import { ReceiptDatePipe } from './pipes/receipt-date.pipe';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    WelcomePageComponent,
    ComponentsUnwrapperDirective,
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
  providers: [AuthService, AuthGuard, WelcomeGuard],
  bootstrap: [AppComponent],
})
export class AppModule { }
