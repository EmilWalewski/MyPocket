import { Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[appComponentsUnwrapper]'
})
export class ComponentsUnwrapperDirective {

  constructor(private el: ElementRef) {

    const element = this.el.nativeElement;
    const parent = this.el.nativeElement.parentElement;

    parent.parentNode.insertBefore(element, parent);
    parent.parentNode.removeChild(parent);

  }

}
