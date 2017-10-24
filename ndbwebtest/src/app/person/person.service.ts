import {BaseService} from '../base.service';
import {Person} from './person';
import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map';

@Injectable()
export class PersonService extends BaseService {

  url = this.ndbUrl + 'person/';

  constructor(private http: Http) {
    super();
  }

  addPerson(person: Person): Observable<Person> {
    return this.http.post(this.url + 'save', JSON.stringify(person), {headers: this.headers}).map(data => data.json() as Person, error => this.handleError);
  }

}
