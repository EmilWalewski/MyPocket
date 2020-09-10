import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountBalanceRoutingModule } from './balance-routing.module';
import { TestComponent } from './components/test/test.component';
import { WalletListComponent } from './components/wallet-list/wallet-list.component';
import { AddWalletComponent } from './components/add-wallet/add-wallet.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { WalletComponent } from './components/wallet/wallet.component';
import { ComponentUnwrapperModule } from 'src/app/globalUnwrapperDirective/component-unwrapper/component-unwrapper.module';



@NgModule({
  declarations: [TestComponent, WalletListComponent, AddWalletComponent, WalletComponent],
  imports: [
    CommonModule,
    AccountBalanceRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    ComponentUnwrapperModule
  ]
})
export class AccountBalanceModule { }
