import {ApplicationError} from '../applicationerror';
import {Country} from '../country/country';
import {State} from '../state/state';
import {District} from './district';
import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {HttpParams} from '@angular/common/http';

@Injectable()
export class DistrictService {
  private url = 'http://localhost:8080/ndb/rest/district/';
  headers = new Headers({
    'Content-Type': 'application/json'
  });
  constructor(private http: Http) {}
  getDistricts(stateId: number): Promise<District[]> {
    const params = new HttpParams();
    params.set('stateId', '' + stateId);
    return this.http.get(this.url + 'search', params).toPromise().then(response => response.json() as District[]).catch(this.handleError);
  }
  addDistrict(district: District): Promise<District> {
    return this.http.post(this.url + 'add', JSON.stringify(district), {headers: this.headers}).toPromise().then(response => response.json() as District).catch(this.handleError);
  }
  handleError(error: any): Promise<any> {
    console.log(error._body);
    const b = JSON.parse(error._body);
    console.log(b);
    if (b instanceof ApplicationError) {
      console.log('ssss' + error);
    }
    return Promise.reject(error);
  }
}
