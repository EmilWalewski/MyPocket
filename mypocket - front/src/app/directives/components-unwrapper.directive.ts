<<<<<<< HEAD
import { Directive, ElementRef, HostListener } from '@angular/core';
=======
import { Directive, ElementRef } from '@angular/core';
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59

@Directive({
  selector: '[appComponentsUnwrapper]'
})
export class ComponentsUnwrapperDirective {

  constructor(private el: ElementRef) {

    const element = this.el.nativeElement;
    const parent = this.el.nativeElement.parentElement;

<<<<<<< HEAD
    console.log(parent);

=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
    parent.parentNode.insertBefore(element, parent);
    parent.parentNode.removeChild(parent);

  }

}
