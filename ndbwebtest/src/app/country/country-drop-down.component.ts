import {Country} from './country';
import {CountryService} from './country.service';
import {Component, Input, EventEmitter, Output, NgModule} from '@angular/core';
import {FormsModule, NgModel} from '@angular/forms';

@Component({
  selector: 'app-countries',
  templateUrl: './country-drop-down.component.html'
})

export class CountryDropDownComponent {
  countries: Country[];
  @Output() selectedCountry = new EventEmitter();

  constructor(private countryService: CountryService) {
    this.countryService.getCountries().then(countries => this.countries = countries);
  }
  onCountryChange(event) {
    console.log('event' + event);
    this.selectedCountry.emit(event);
  }
}
