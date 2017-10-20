import {Country} from './country';
import {CountryService} from './country.service';
import {Component, OnInit} from '@angular/core';
import {FormsModule, FormControl, ReactiveFormsModule} from '@angular/forms';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-country-list',
  templateUrl: './country-list.component.html'
})

export class CountryListComponent implements OnInit {

  countries: Country[];
  countries1: Country[];
  searchTerm = new FormControl();
  searchText: string;

  constructor(
    private countryService: CountryService) {
    this.searchTerm.valueChanges.distinctUntilChanged().debounceTime(400).subscribe(searchTerm =>
      this.countryService.searchCountries(searchTerm).subscribe(countries1 => this.countries1 = countries1));
  }

  ngOnInit(): void {
    this.getCountries();
  }
  getCountries(): void {
    this.countryService.getCountries().subscribe(countries => this.countries = countries);
    console.log('after');
  }
}

