import {ApplicationError} from '../applicationerror';
import {BaseService} from '../base.service';
import {Country} from '../country/country';
import {State} from '../state/state';
import {District} from './district';
import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map';

@Injectable()
export class DistrictService extends BaseService {
  private url = this.ndbUrl + 'district/';
  constructor(private http: HttpClient) {
    super();
  }
  getDistricts(stateId: number): Observable<District[]> {
    console.log('stateid ' + stateId);
    const params = new HttpParams().append('stateId', '' + stateId);
//    myParams.set('stateId', '' + stateId);
    return this.http.get(this.url + 'search', {params: params}).map(response => response as District[], error => super.handleError);
  }
  addDistrict(district: District): Observable<District> {
    return this.http.post(this.url + 'add', JSON.stringify(district), {headers: this.headers}).map(response => response as District,
      error => super.handleError);
  }
}
