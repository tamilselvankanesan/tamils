import {AppRoutingModule} from './app-routing.module';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {CountryComponent} from './country/country.component';
import {CountryService} from './country/country.service';
import {StateComponent} from './state/state.component';
import {StateService} from './state/state.service';
import {RouterModule} from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    CountryComponent,
    StateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [CountryService, StateService],
  bootstrap: [AppComponent]
})
export class AppModule {}
