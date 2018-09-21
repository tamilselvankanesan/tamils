import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';

import {RouterModule, Route} from '@angular/router';
import { AppComponent } from './app.component';
import { REMentComponent } from './r-e-ment/r-e-ment.component';

import {DataTableModule,SharedModule} from 'primeng/primeng';

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
    BrowserModule, FormsModule, DataTableModule, SharedModule, 
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
