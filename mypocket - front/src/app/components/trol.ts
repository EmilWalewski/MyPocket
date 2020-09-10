import {
    Component, AfterViewInit, ViewChild,
    ViewContainerRef, TemplateRef, ComponentFactoryResolver, Injector
} from '@angular/core';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';

@Component({
    selector: 'app-toss',
    template: `
    <h1>Application Content</h1>
    <ng-container #container></ng-container> <!-- embed view here -->
    <h3>End of Application</h3>
    <ng-template #template>
      <h1>Template Content</h1>
      <h3>Dynamically Generated!</h3>
    </ng-template>
    `,
    entryComponents: [WelcomePageComponent]
})
export class ExampleComponent implements AfterViewInit {
    @ViewChild('template', { read: TemplateRef }) tpl: TemplateRef<any>;
    @ViewChild('container', { read: ViewContainerRef }) ctr: ViewContainerRef;

    constructor(private container: ComponentFactoryResolver, private injector: Injector) {
    }

    ngAfterViewInit() {

        // this.ctr.createEmbeddedView(this.tpl);

        const factory = this.container.resolveComponentFactory(WelcomePageComponent);
        // const comp = factory.create(this.injector);
        this.ctr.createComponent(factory);
        // let view = comp.hostView;
    }
}
