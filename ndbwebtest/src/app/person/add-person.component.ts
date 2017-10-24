import {Component} from '@angular/core';
import {Person} from './person';
import {PersonService} from './person.service';
@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html'
})
export class AddPersonComponent {
  person = new Person();
  selectedStateId: number;
  selectedDistrictId: number;
  stateDropdown = 'state';
  constructor(private personService: PersonService) {

  }
  addPerson() {
    let person = new Person();
//    person.state = this.se
    this.personService.addPerson(person);
  }
  onStateChange(state) {
    if (state) {
      this.selectedStateId = state.id;
    } else {
      this.selectedStateId = 0;
    }
    this.selectedDistrictId = 0;
  }
  onDistrictChange(district) {
    if (district) {
      this.selectedDistrictId = district.id;
    } else {
      this.selectedDistrictId = 0;
    }


  }
}