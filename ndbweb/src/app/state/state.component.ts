import {Country} from '../country/country';
import {CountryService} from '../country/country.service';
import {State} from './state';
import {StateService} from './state.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Params} from '@angular/router';
import { Location } from '@angular/common';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-sate',
  templateUrl: './sate.component.html',
  styleUrls: ['./state.component.css']
})
export class StateComponent implements OnInit {

  states: State[];
  selectedCountry: Country;
  //  selectedCountryId: number;

  constructor(
    private stateService: StateService,
    private countryService: CountryService,
    private route: ActivatedRoute,
    private location: Location) {}

  ngOnInit() {
    // this.stateService.getAllStates().then(states => this.states = states);
    this.route.paramMap.switchMap((params: Params) =>
      this.countryService.getCountry(params.get('code'))).subscribe(country => this.selectedCountry = country);
    this.route.paramMap.switchMap((params: Params) =>
      this.stateService.getStates(+params.get('code'))).subscribe(states => this.states = states);
  }
  goBack(): void {
    this.location.back();
  }
}
