import {Country} from '../country/country';
import {State} from '../state/state';
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

  constructor(private districtService: DistrictService) {

  }
  addDistrict() {

  }
  onCountrySelect(event) {

  }
  onStateSelect(event) {

  }
}
