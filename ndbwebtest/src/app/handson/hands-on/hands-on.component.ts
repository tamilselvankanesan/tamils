import {Country} from '../../country/country';
import { CountryService } from '../../country/country.service';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-hands-on',
  templateUrl: './hands-on.component.html',
  styleUrls: ['./hands-on.component.css']
})
export class HandsOnComponent implements OnInit {

  countries: Country[];
  selectedCountry: Country;

  constructor(private countryService: CountryService) {}

  ngOnInit() {
    this.countryService.getCountries().then(countries => this.countries = countries);
  }

}
