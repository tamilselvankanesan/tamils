import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';

import {RouterModule, Route} from '@angular/router';
import { AppComponent } from './app.component';
import { REMentComponent } from './r-e-ment/r-e-ment.component';

import {DataTableModule,SharedModule,DropdownModule,DialogModule} from 'primeng/primeng';
import { REService } from './r-e-ment/r-e.service';

const routes : Route[] = [

  {
    path: 'rules', component: REMentComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    REMentComponent
  ],
  imports: [
    BrowserModule, FormsModule, DataTableModule, SharedModule, DropdownModule,DialogModule,
    RouterModule.forRoot(routes),BrowserAnimationsModule
  ],
  exports: [RouterModule],
  providers: [REService],
  bootstrap: [AppComponent]
})
export class AppModule { }
