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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminComponent } from './admin/admin.component';
import { AdminService } from './admin/admin.service';
import { AddStateComponent } from './state/add-state/add-state.component';

@NgModule({
  declarations: [
    AppComponent,
    CountryComponent,
    StateComponent,
    CountryFilterPipe,
    AdminComponent,
    AddStateComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [CountryService, StateService, AdminService],
  bootstrap: [AppComponent]
})
export class AppModule {}
