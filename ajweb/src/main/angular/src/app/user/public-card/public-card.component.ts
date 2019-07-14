import { Component, OnInit } from '@angular/core';
import { Person } from 'src/app/models/person/person';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-public-card',
  templateUrl: './public-card.component.html',
  styleUrls: ['./public-card.component.css']
})
export class PublicCardComponent implements OnInit {

  person = new Person();
  constructor(private personService: PersonService) {
    
   }

  ngOnInit() {
    // this.person = this.personService.getPerson();
    this.personService.person$.subscribe(data => this.person = data);
  }

}
