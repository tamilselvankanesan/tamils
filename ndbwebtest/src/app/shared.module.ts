import { StateDropDownComponent } from './state/state-drop-down.component';
import {StateListComponent} from './state/state-list.component';
import {StateService} from './state/state.service';
import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
@NgModule(
  {
    imports: [ReactiveFormsModule, CommonModule, FormsModule],
    declarations: [StateListComponent, StateDropDownComponent],
    exports: [StateListComponent, StateDropDownComponent],
    providers: []
  }
)

export class SharedModule {}
