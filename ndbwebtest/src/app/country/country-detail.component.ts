import {Country} from './country';
import {CountryService} from './country.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {FormsModule, FormControl, ReactiveFormsModule} from '@angular/forms';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-country-detail',
  templateUrl: './country-detail.component.html'
})

export class CountryDetailComponent implements OnInit {
  selectedCountry: Country;
  constructor(
    private countryService: CountryService,
    private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.route.paramMap.switchMap((params: Params) => this.countryService.getCountry(params.get('code'))).subscribe(country => this.selectedCountry = country);
  }
}
