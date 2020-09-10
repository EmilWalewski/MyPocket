import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComponentsUnwrapperDirective } from 'src/app/directives/components-unwrapper.directive';



@NgModule({
  declarations: [ComponentsUnwrapperDirective],
  imports: [
    CommonModule
  ],
  exports: [ComponentsUnwrapperDirective]
})
export class ComponentUnwrapperModule { }
