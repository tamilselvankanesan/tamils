import {ApplicationError} from '../applicationerror';
import {BaseService} from '../base.service';
import {Country} from '../country/country';
import {State} from '../state/state';
import {District} from './district';
import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions, URLSearchParams} from '@angular/http';
import {HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map';

@Injectable()
export class DistrictService extends BaseService {
  private url = this.ndbUrl + 'district/';
  headers = new Headers({
    'Content-Type': 'application/json'
  });
  constructor(private http: Http) {
    super();
  }
  getDistricts(stateId: number): Observable<District[]> {
    let myHeaders = new Headers();
    myHeaders.set('Content-Type', 'application/json');
    const myParams = new URLSearchParams();
    myParams.set('stateId', '' + stateId);
    return this.http.get(this.url + 'search', {search: myParams}).map(response => response.json() as District[], error => super.handleError);
  }
  addDistrict(district: District): Observable<District> {
    console.log('add d');
    return this.http.post(this.url + 'add', JSON.stringify(district), {headers: this.headers}).map(response => response.json() as District,
      error => super.handleError);
  }
}
