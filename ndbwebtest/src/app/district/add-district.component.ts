import {ApplicationError} from '../applicationerror';
import {Country} from '../country/country';
import {State} from '../state/state';
import {StateService} from '../state/state.service';
import {District} from './district';
import {DistrictService} from './district.service';
import {Component} from '@angular/core';

@Component({
  selector: 'app-add-district',
  templateUrl: './add-district.component.html'
})
export class AddDistrictComponent {
  district: District;
  selectedCountry: Country;
  selectedState: State;
  states: State[];
  districtName: string;

  constructor(private districtService: DistrictService,
    private stateService: StateService) {

  }
  addDistrict() {
    const district = new District();
    district.name = this.districtName;
    district.state = this.selectedState;
    this.districtService.addDistrict(district).then(result => {}).catch(e => {
      console.log('Application Error Occurred.');
    });
  }
  onCountrySelect(event) {
    this.selectedCountry = event;
    this.stateService.getStates(event.code).then(states => this.states = states);
  }
  onStateSelect(event) {
    this.selectedState = event;
  }
}
