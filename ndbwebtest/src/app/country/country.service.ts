import {BaseService} from '../base.service';
import {Country} from './country';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map';
// import 'rxjs/add/operator/toPromise';

@Injectable()
export class CountryService extends BaseService {
  private header = new Headers({'Content-type': 'application/json'});
  private countryURL = 'http://localhost:8080/ndb/rest/countries';

  constructor(private http: HttpClient) {
    super();
  }

  getCountries(): Observable<Country[]> {
    return this.http.get(this.countryURL).map(
      data => data as Country[],
      error => super.handleError);
  }

  searchCountries(searchTerm: String): Observable<Country[]> {
    let countries: Country[];
    return this.http.get(this.countryURL + '/' + searchTerm).map(data => data as Country[]);
  }

  getCountry(code: string): Observable<Country> {
    console.log('code->' + code);
    return this.http.get(this.countryURL + '/find/' + code).map(data => data as Country, error => super.handleError);
  }
}
