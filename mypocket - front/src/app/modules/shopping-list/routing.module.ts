import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ShoppingListComponent } from './components/shopping-list/shopping-list.component';
import { TestComponent } from './components/test/test.component';
import { ShoppingDetailsComponent } from './components/shopping-details/shopping-details.component';

const routes: Routes = [
    { path: '',
      children: [
          {
            path: '', component: ShoppingListComponent
          },
          {
<<<<<<< HEAD
=======
            path: 'shop', component: TestComponent
          },
          {
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
            path: ':id', component: ShoppingDetailsComponent
          }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ShoppingListRoutingModule { }
