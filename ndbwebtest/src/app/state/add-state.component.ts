import {Country} from '../country/country';
import {Component} from '@angular/core';
@Component({
  selector: 'app-add-state',
  templateUrl: './add-state.component.html'
})

export class AddStateComponent {
  selectedCountry: Country;
  countryChanged(newCountry) {
    this.selectedCountry = newCountry;
    console.log(this.selectedCountry);
  }
}

