import { Component, OnInit } from '@angular/core';
import { Person } from 'src/app/models/person/person';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  person = new Person();
  constructor(private personService: PersonService) {
    this.personService.person$.subscribe(data => this.person = data);
   }

  ngOnInit() {
  }

}
