import {State} from './state';
import {Component, Input, EventEmitter, Output, NgModule} from '@angular/core';
import {FormsModule, NgModel} from '@angular/forms';

const statesArr: State[] = [{
  id: 1, code: 'TN', name: 'Tamil Nadu', country: null
}];

@Component({
  selector: 'app-state-dropdown',
  templateUrl: './state-drop-down.component.html'
})

export class StateDropDownComponent {
  @Output() selectedCountry = new EventEmitter();
  @Input() states: State[];
  constructor() {
    // this.states = statesArr;
  }
  onStateChange(event) {
    console.log('selected state id: ' + event);
    this.selectedCountry.emit(event);
  }
}
