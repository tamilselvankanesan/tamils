import {Country} from '../country/country';
import {CountryService} from '../country/country.service';
import {State} from './state';
import {StateService} from './state.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Params} from '@angular/router';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-sate',
  templateUrl: './sate.component.html',
  styleUrls: ['./state.component.css']
})
export class StateComponent implements OnInit {

  states: State[];
  selectedCountry: Country;
  selectedCountryId: number;

  constructor(
    private stateService: StateService,
    private countryService: CountryService,
    private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.paramMap.switchMap((params: Params) =>
      this.stateService.getStates(params.get('id'))).subscribe(states => this.states = states);
  }
}
