import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './guards/auth/auth.guard';
import { WelcomeGuard } from './guards/welcome/welcome.guard';


const routes: Routes = [
  { path: '', component: WelcomePageComponent, canActivate: [WelcomeGuard] },
  {
    path: 'mypocket', canActivate: [AuthGuard],
    children: [
      {
        path: '',
        component: HomeComponent,
        pathMatch: 'full'
      },
      {
        path: 'list',
        loadChildren: () => import('./modules/shopping-list/shopping-list.module').then(m => m.ShoppingListModule)
<<<<<<< HEAD
      },
      {
        path: 'account',
        loadChildren: () => import('./modules/account-balance/account-balance.module').then(m => m.AccountBalanceModule)
      }
    ]
  },
  {
    path: '**',
    redirectTo: '/',
    pathMatch: 'full'
=======
      }
    ]
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
