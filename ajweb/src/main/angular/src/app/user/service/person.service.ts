import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Person } from 'src/app/models/person/person';
import { BaseService } from 'src/app/base.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonService extends BaseService {

  person: Person;
  personSubject = new BehaviorSubject<Person>(new Person());
  public person$ = this.personSubject.asObservable();

  constructor(private http: HttpClient) {
    super();
  }

  getPersonByUserId(userId) {
    console.log('user Id is '+userId);
    this.http.get(this.baseUrl + 'person/user/' + userId, { headers: this.headers }).map(data => data as Person, this.handleError).subscribe(data => {
      this.setPerson(data);
    });
  }
  setPerson(data: Person) {
    console.log('person id is '+data.personId);
    this.person = data;
    this.personSubject.next(data);
  }
  getPerson(){
    return this.person;
  }
}
