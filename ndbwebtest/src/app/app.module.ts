import {AppRoutingModule} from './app-routing.module';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {AppComponent} from './app.component';
import {CountryListComponent} from './country/country-list.component';
import {CountryModule} from './country/country.module';
import {CountryService} from './country/country.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AdminComponent} from './admin/admin.component';
import {AdminService} from './admin/admin.service';
import {AddCityComponent} from './city/add-city.component';
import { CityService } from './city/city.service';
import {CountryDropDownComponent} from './country/country-drop-down.component';
import { AddDistrictComponent } from './district/add-district.component';
import { DistrictService } from './district/district.service';
import {AddStateComponent} from './state/add-state.component';
import {HandsOnComponent} from './handson/hands-on/hands-on.component';
import { PersonService } from './person/person.service';
import { SharedModule } from './shared.module';
import {StateService} from './state/state.service';
import {CommonModule} from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    AddStateComponent,
    CountryDropDownComponent,
    HandsOnComponent,
    AddCityComponent,
    AddDistrictComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule, HttpModule, CountryModule, AppRoutingModule, FormsModule, SharedModule
  ],
  providers: [AdminService, StateService, DistrictService, CityService, PersonService],
  bootstrap: [AppComponent]
})
export class AppModule {}
