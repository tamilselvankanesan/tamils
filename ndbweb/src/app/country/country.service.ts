import {Country} from './country';
import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';

const COUNTRIES: Country[] = [
  {code: 'IN', name: 'India'},
  {code: 'US', name: 'USA'},
  {code: 'RU', name: 'Russia'},
  {code: 'CN', name: 'China'},
  {code: 'AU', name: 'Australia'}
];

@Injectable()
export class CountryService {
  private header = new Headers({'Content-type': 'application/json'});
  private countryURL = 'http://localhost:8080/ndb/rest/countries';
  private countries: Country[];

  constructor(private http: Http) {}
  getCountries(): Promise<Country[]> {
    return this.http.get(this.countryURL).toPromise().then(response => response.json().data as Country[]).catch(this.handleError);
    //    return Promise.resolve(COUNTRIES);
  }

  getCountry(code: string): Promise<Country> {
    return this.getCountries().then(countries => countries.find(country => country.code === code));
  }
  handleError(error: any): Promise<any> {
    console.log('eeeeeee');
    console.error('error occured', error);
    return Promise.reject(error.message || error);
  }
}
