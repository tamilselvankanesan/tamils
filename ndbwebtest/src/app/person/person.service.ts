import {BaseService} from '../base.service';
import {Person} from './person';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map';

@Injectable()
export class PersonService extends BaseService {

  url = this.ndbUrl + 'person/';

  constructor(private http: HttpClient) {
    super();
  }

  addPerson(person: Person): Observable<Person> {
    return this.http.post(this.url + 'save', JSON.stringify(person), {headers: this.headers}).map(data => data as Person, error => this.handleError);
  }

  search(searchParam: string): Observable<Person[]> {
    return this.http.get(this.url + 'search/' + searchParam).map(data => data as Person[], error => this.handleError);
  }

  getPersonDetail(id: number): Observable<Person> {
    return this.http.get(this.url + '/' + id).map(data => data as Person, error => this.handleError);
  }
}
