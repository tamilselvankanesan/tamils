import {Country} from '../country/country';
import {State} from './state';
import {StateService} from './state.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Params} from '@angular/router';

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
    private route: ActivatedRoute) {}

  ngOnInit() {
    console.log(this.route.snapshot.paramMap.get('id'));

    //    this.route.paramMap.switch
    this.route.params.forEach((params: Params) => {
      this.selectedCountryId = params['id'];
    });
    console.log('testing');
    this.getStates();
  }
  getStates(): void {
    this.stateService.getAllStates().then(states => this.states = states);
    //    this.stateService.getStates(this.selectedCountryId).then(states => this.states = states);
  }
}
