import {AppRoutingModule} from './app-routing.module';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {CountryComponent} from './country/country.component';
import {CountryService} from './country/country.service';
import {StateComponent} from './state/state.component';
import {StateService} from './state/state.service';
import {RouterModule} from '@angular/router';
import {HttpModule} from '@angular/http';
import { CountryFilterPipe } from './country/country-filter.pipe';
import { FormsModule } from '@angular/forms';
import { AdminComponent } from './admin/admin.component';
import { AdminService } from './admin/admin.service';

@NgModule({
  declarations: [
    AppComponent,
    CountryComponent,
    StateComponent,
    CountryFilterPipe,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule
  ],
  providers: [CountryService, StateService, AdminService],
  bootstrap: [AppComponent]
})
export class AppModule {}
