import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';

import {RouterModule, Routes, Route} from '@angular/router';
import { AppComponent } from './app.component';
import { RuleEnrichmentComponent } from './rule-enrichment/rule-enrichment.component';


const routes : Route[] = [

  {
    path: '', component: RuleEnrichmentComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    RuleEnrichmentComponent
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
