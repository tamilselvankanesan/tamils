import {District} from '../district/district';
import {State} from '../state/state';
import {StateService} from '../state/state.service';
import {City} from './city';
import {CityService} from './city.service';
import {Component} from '@angular/core';

@Component({
  selector: 'app-add-city',
  templateUrl: './add-city.component.html'
})
export class AddCityComponent {
  states: State[];
  selectedState: State;
  selectedDistrict: District;
  name: string;
  message: string;
  success = true;
  constructor(private stateService: StateService, private cityService: CityService) {
  }
  onCountrySelect(selectedCountry) {
    if (selectedCountry) {
      this.stateService.getStates(selectedCountry.code).subscribe(states => this.states = states);
    } else {
      this.states.length = 0;
    }
  }
  onStateSelect(selState) {
    this.selectedState = selState;
    console.log('city - state ' + selState);
  }
  onDistrictSelect(event) {
    console.log('district selected - ' + event);
    this.selectedDistrict = event;
  }
  addCity() {
    let newCity = new City();
    this.message = '';
    newCity.name = this.name.trim();
    newCity.district = this.selectedDistrict;
    this.cityService.addCity(newCity).subscribe(city => {
      this.message = city.message;
      this.success = !city.error;
    });
  }
}
