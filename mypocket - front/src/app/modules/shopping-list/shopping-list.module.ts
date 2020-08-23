import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShoppingListRoutingModule } from './routing.module';
import { Routes } from '@angular/router';
import { ShoppingListComponent } from './components/shopping-list/shopping-list.component';
import { TestComponent } from './components/test/test.component';
import { ReceiptDatePipe } from 'src/app/pipes/receipt-date.pipe';
import { ShoppingDetailsComponent } from './components/shopping-details/shopping-details.component';


@NgModule({
  declarations: [
    ShoppingListComponent,
    ShoppingDetailsComponent,
    TestComponent,
    ReceiptDatePipe
  ],
  imports: [
    CommonModule,
    ShoppingListRoutingModule
  ]
})
export class ShoppingListModule { }
