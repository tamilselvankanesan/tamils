import {Country} from '../country/country';
import {State} from './state';
import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

const STATES: State[] = [
  {id: 1, name: 'Tamil Nadu', code: 'TN', country: null},
  {id: 2, name: 'Andhra Pradesh', code: 'AP', country: null},
  {id: 3, name: 'Karnataka', code: 'KA', country: null},
  {id: 4, name: 'Kerala', code: 'KL', country: null}
];
@Injectable()
export class StateService {

  private statesURL = 'http://localhost:8080/ndb/rest/state/';

  constructor(private http: Http) {}
  getAllStates(): Promise<State[]> {
    return Promise.resolve(STATES);

  }
  getStates(countryCode: string): Promise<State[]> {
    return this.http.get(this.statesURL + 'country/' + countryCode).toPromise().then(response => response.json() as State[]).catch(this.handleError);
  }
  handleError(error: any): Promise<any> {
    console.error('error occured', error);
    return Promise.reject(error.message || error);
  }
}
