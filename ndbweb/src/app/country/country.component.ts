import {Component, OnInit} from '@angular/core';
import {Country} from './country';
import {CountryService} from './country.service';

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent implements OnInit {
  countries: Country[];
  constructor(private countryService: CountryService) {
  }

  ngOnInit() {
    this.getCountries();
  }

  getCountries(): void {
    this.countryService.getCountries().then(countries => this.countries = countries);
  }

}
