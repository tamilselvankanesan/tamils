import {Country} from '../country/country';
import {State} from './state';
import {StateService} from './state.service';
import {Component, OnInit, Input, OnChanges} from '@angular/core';
import {FormsModule, FormControl, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-state-list',
  templateUrl: './state-list.component.html'
})

export class StateListComponent implements OnInit, OnChanges {
  states: State[];
  @Input('selCountry') selCountry: Country;
  hidden = false;

  constructor(private stateService: StateService) {}

  ngOnInit(): void {
    console.log('selected country code->' + this.selCountry.code);
    this.getStates(this.selCountry.code).then(states => this.states = states);
  }

  ngOnChanges(changes: any) {
  }

  getStates(countryCode: string): Promise<State[]> {
    return this.stateService.getStates(countryCode);
  }
}
