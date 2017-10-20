import {Country} from '../country/country';
import {State} from './state';
import {StateService} from './state.service';
import {Component} from '@angular/core';
@Component({
  selector: 'app-add-state',
  templateUrl: './add-state.component.html'
})

export class AddStateComponent {
  selectedCountry: Country;
  newState = new State();
  success = true;
  message: string;

  constructor(private stateService: StateService) {}
  countryChanged(newCountry) {
    this.selectedCountry = newCountry;
  }
  addNewState() {
    this.newState.country = this.selectedCountry;
    this.stateService.createState(this.newState).subscribe(state => {
      console.log('message -> ' + state.message);
      this.success = !(state.error);
      this.message = state.message;
    });
  }
}

