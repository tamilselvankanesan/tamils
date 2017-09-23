import {Component, OnInit} from '@angular/core';
import {Country} from './country';
import {CountryService} from './country.service';
import {Location} from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CountryFilterPipe } from './country-filter.pipe';

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent implements OnInit {
  countries: Country[];
  searchText: string;
  cName: string;
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
    console.log('after');
  }
}
