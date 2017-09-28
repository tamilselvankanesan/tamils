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

  constructor(private stateService: StateService) {}

  ngOnInit(): void {
  }

  ngOnChanges(changes: any) {
    console.log('selected country code->' + this.selCountry.code);
    this.getStates(this.selCountry.code).then(states => this.states = states);
  }

  getStates(countryCode: string): Promise<State[]> {
    return this.stateService.getAllStates();
  }
}
