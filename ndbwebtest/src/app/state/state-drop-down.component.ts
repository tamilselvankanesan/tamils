import {State} from './state';
import {StateService} from './state.service';
import {Component, Input, EventEmitter, Output, NgModule, OnInit} from '@angular/core';
import {FormsModule, NgModel} from '@angular/forms';

@Component({
  selector: 'app-state-dropdown',
  templateUrl: './state-drop-down.component.html'
})

export class StateDropDownComponent implements OnInit {
  @Output() selectedState = new EventEmitter();
  @Input() states: State[];
  @Input() name: string;
  selState: State;
  constructor(private stateService: StateService) {
  }
  ngOnInit(): void {
    this.stateService.getStates('IN').subscribe(states => this.states = states);
  }
  onStateChange(event) {
    this.selectedState.emit(event);
  }
}
