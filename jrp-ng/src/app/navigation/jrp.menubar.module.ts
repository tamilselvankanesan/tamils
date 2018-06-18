import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MenubarModule} from 'primeng/menubar';
import {ButtonModule} from 'primeng/button';
import {TabViewModule} from 'primeng/tabview';
import {CodeHighlighterModule} from 'primeng/codehighlighter';

@NgModule({
  imports: [
    CommonModule,
    MenubarModule,
    ButtonModule,
    TabViewModule,
    CodeHighlighterModule
  ]
})
export class JrpMenubarModule {}
