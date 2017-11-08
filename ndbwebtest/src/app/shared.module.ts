import {CityDropdownComponent} from './city/city-dropdown.component';
import {DistrictListComponent} from './district/district-list.component';
import {AddPersonComponent} from './person/add-person.component';
import {StateDropDownComponent} from './state/state-drop-down.component';
import {StateListComponent} from './state/state-list.component';
import {StateService} from './state/state.service';
import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule(
  {
    imports: [ReactiveFormsModule, CommonModule, FormsModule, HttpClientModule],
    declarations: [StateListComponent, StateDropDownComponent, DistrictListComponent, AddPersonComponent, CityDropdownComponent],
    exports: [StateListComponent, StateDropDownComponent, DistrictListComponent, AddPersonComponent, CityDropdownComponent],
    providers: []
  }
)

export class SharedModule {}
