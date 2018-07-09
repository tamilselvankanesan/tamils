import {Component, OnInit} from '@angular/core';
import {Country} from './country';
import {CountryService} from './country.service';
import {Location} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {CountryFilterPipe} from './country-filter.pipe';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import {ReactiveFormsModule, FormControl} from '@angular/forms';

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent implements OnInit {
  countries: Country[];
  countries1: Country[];
  searchText: string;
  cName: string;
  term = new FormControl();
  constructor(
    private countryService: CountryService,
    private location: Location) {
    this.term.valueChanges.debounceTime(400).distinctUntilChanged().subscribe(term => this.countryService.searchCountries(term).then(countries => this.countries1 = countries));
    // this.term.valueChanges.debounceTime(400).switchMap(term => this.countryService.searchCountries(term));
  }

  ngOnInit() {
    this.getCountries();
  }

  getCountries(): void {
    this.countryService.getCountries().then(countries => this.countries = countries);
    console.log('after');
  }
}
