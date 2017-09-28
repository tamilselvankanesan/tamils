import {Country} from './country';
import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class CountryService {
  private header = new Headers({'Content-type': 'application/json'});
  private countryURL = 'http://localhost:8080/ndb/rest/countries';

  constructor(private http: Http) {}

  getCountries(): Promise<Country[]> {
    return this.http.get(this.countryURL).toPromise().then(respone => respone.json() as Country[]).catch(this.handleError);
  }
  searchCountries(searchTerm: String): Promise<Country[]> {
    return this.http.get(this.countryURL + '/' + searchTerm).toPromise().then(response => response.json() as Country[]).catch(this.handleError);
  }

  getCountry(code: string): Promise<Country> {
    console.log('code->' + code);
    return this.http.get(this.countryURL + '/find/' + code).toPromise().then(response => response.json() as Country).catch(this.handleError);
  }

  handleError(error: any): Promise<any> {
    console.error('error occured', error);
    return Promise.reject(error.message || error);
  }
}
