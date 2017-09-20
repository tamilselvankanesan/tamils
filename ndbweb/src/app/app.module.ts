import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { CountryComponent } from './country/country.component';
import { CountryService } from './country/country.service';
import { StateComponent } from './state/state.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    CountryComponent,
    StateComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
    {path: 'states/:id', component: StateComponent}
    ])
  ],
  providers: [CountryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
