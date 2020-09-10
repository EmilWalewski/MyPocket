import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WalletListComponent } from './components/wallet-list/wallet-list.component';
import { AddWalletComponent } from './components/add-wallet/add-wallet.component';

const routes: Routes = [
    {
        path: '',
        children: [
            {
                path: '',
                component: WalletListComponent,
            },
            {
                path: 'wallet',
                component: AddWalletComponent
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AccountBalanceRoutingModule { }
