import {Component, OnInit} from '@angular/core';
import {Country} from './country';
import {CountryService} from './country.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent implements OnInit {
  countries: Country[];
  constructor(
    private countryService: CountryService,
    private location: Location
  ) {
  }

  ngOnInit() {
    this.getCountries();
  }

  getCountries(): void {
    this.countryService.getCountries().then(countries => this.countries = countries);
  }
}
