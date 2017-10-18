import {Country} from '../country/country';
import {State} from '../state/state';
import {District} from './district';
import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {HttpParams} from '@angular/common/http';

@Injectable()
export class DistrictService {
  private url = 'http://localhost:8080/ndb/rest/district/';

  constructor(private http: Http) {}
  getDistricts(stateId: number): Promise<District[]> {
    const params = new HttpParams();
    params.set('stateId', '' + stateId);
    return this.http.get(this.url + 'search', params).toPromise().then(response => response.json() as District[]).catch(this.handleError);
  }
  addDistrict(district: District): Promise<District> {
    return this.http.post(this.url + 'add', JSON.stringify(district)).toPromise().then(response => response.json() as District).catch(this.handleError);
  }
  handleError(error: any): Promise<any> {
    console.error('error occured', error);
    return Promise.reject(error.message || error);
  }
}
