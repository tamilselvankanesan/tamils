import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';

import {RouterModule, Route} from '@angular/router';
import { AppComponent } from './app.component';
import { REMentComponent } from './r-e-ment/r-e-ment.component';


const routes : Route[] = [

  {
    path: '', component: REMentComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    REMentComponent
  ],
  imports: [
    BrowserModule, FormsModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
