import {Person} from './person';
import {PersonService} from './person.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, ParamMap} from '@angular/router';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-person-detail',
  templateUrl: './person-detail.component.html'
})
export class PersonDetailComponent implements OnInit {
  selectedPerson: Person;
  constructor(private route: ActivatedRoute, private personService: PersonService) {}
  ngOnInit(): void {
    this.route.paramMap.switchMap((params: Params) => {
      return this.personService.getPersonDetail(+params.get('id'));
    }).subscribe(data => this.selectedPerson = data);
  }
}
