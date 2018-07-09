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
  success = true;
  message: string;

  constructor(private districtService: DistrictService,
    private stateService: StateService) {

  }
  addDistrict() {
    const district = new District();
    district.name = this.districtName;
    district.state = this.selectedState;
    this.districtService.addDistrict(district).subscribe(result => {
      this.success = !(result.error);
      this.message = result.message;
    });
  }
  onCountrySelect(event) {
    this.selectedCountry = event;
    this.stateService.getStates(event.code).subscribe(states => this.states = states);
  }
  onStateSelect(event) {
    this.selectedState = event;
  }
}
