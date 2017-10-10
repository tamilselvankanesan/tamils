import {Country} from '../../country/country';
import {CountryService} from '../../country/country.service';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-hands-on',
  templateUrl: './hands-on.component.html',
  styleUrls: ['./hands-on.component.css']
})
export class HandsOnComponent implements OnInit {

  countries: Country[];
  selectedCountry: Country;
  sampleText = 'Hello';
  countryArr = [
    new Country('IN', 'India', null),
    new Country('USA', 'United States of America', null)
  ];

  selectedCtry = this.countryArr[0];

  constructor(private countryService: CountryService) {}

  ngOnInit() {
    this.countryService.getCountries().then(countries => this.countries = countries);
  }

}
