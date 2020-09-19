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
