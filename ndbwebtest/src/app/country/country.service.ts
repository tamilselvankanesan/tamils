import {BaseService} from '../base.service';
import {Country} from './country';
import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map';
// import 'rxjs/add/operator/toPromise';

@Injectable()
export class CountryService extends BaseService {
  private header = new Headers({'Content-type': 'application/json'});
  private countryURL = 'http://localhost:8080/ndb/rest/countries';

  constructor(private http: Http) {
    super();
  }

  getCountries(): Observable<Country[]> {
    return this.http.get(this.countryURL).map(
      data => data.json() as Country[],
      error => super.handleError);
  }

  searchCountries(searchTerm: String): Observable<Country[]> {
    let countries: Country[];
    return this.http.get(this.countryURL + '/' + searchTerm).map(data => data.json() as Country[]);
  }

  getCountry(code: string): Observable<Country> {
    console.log('code->' + code);
    return this.http.get(this.countryURL + '/find/' + code).map(data => data.json() as Country, error => super.handleError);
  }
}
