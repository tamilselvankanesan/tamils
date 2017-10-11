import { State } from './state';
import {Component, Input, EventEmitter, Output, NgModule} from '@angular/core';
import {FormsModule, NgModel} from '@angular/forms';

@Component({
  selector: 'app-state-dropdown',
  templateUrl: './state-drop-down.component.html'
})

export class StateDropDownComponent {
  @Output() selectedCountry = new EventEmitter();
  @Input() states: State[];
  constructor() {
  }
  onCountryChange(event) {
  }
}
