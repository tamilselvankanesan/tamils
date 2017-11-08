import {BaseService} from '../base.service';
import {City} from './city';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map';

@Injectable()
export class CityService extends BaseService {
  url = this.ndbUrl + 'city/';
  constructor(private http: HttpClient) {
    super();
  }
  addCity(city: City): Observable<City> {
    return this.http.post(this.url + 'save', JSON.stringify(city), {headers: this.headers}).map(
      response => response as City, error => super.handleError);
  }
  getCities(districtId: number): Observable<City[]> {
    return this.http.get(this.url + '' + districtId).map(data => data as City[], error => super.handleError);
  }
}
