import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Person } from '../models/person/person';
import { PersonService } from '../user/service/person.service';

@Component({
  selector: 'app-left-nav',
  templateUrl: './left-nav.component.html',
  styleUrls: ['./left-nav.component.css']
})
export class LeftNavComponent implements OnInit {

  person = new Person();
  constructor(private router: Router, private personService: PersonService) {
    this.personService.person$.subscribe(data => this.person = data);
   }

  ngOnInit() {
  }

  gotoDashboard(){
    this.router.navigate(['/dashboard']); // navigate to home page
  }

}
