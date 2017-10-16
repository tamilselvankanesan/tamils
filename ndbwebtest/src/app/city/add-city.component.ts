import {State} from '../state/state';
import {StateService} from '../state/state.service';
import {Component} from '@angular/core';

@Component({
  selector: 'app-add-city',
  templateUrl: './add-city.component.html'
})
export class AddCityComponent {
  states: State[];
  selectedState: State;
  constructor(private stateService: StateService) {
  }
  onCountrySelect(selectedCountry) {
    if (selectedCountry) {
      this.stateService.getStates(selectedCountry.code).then(states => this.states = states);
    } else {
      this.states.length = 0;
    }
  }
}
