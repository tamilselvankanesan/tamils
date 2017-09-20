import {Country} from './country';
import {Injectable} from '@angular/core';

const COUNTRIES: Country[] = [
  {id: 1, name: 'India'},
  {id: 2, name: 'USA'},
  {id: 3, name: 'Russia'},
  {id: 4, name: 'China'},
  {id: 5, name: 'Australia'}
];

@Injectable()
export class CountryService {

  constructor() {}
  getCountries(): Promise<Country[]> {
    return Promise.resolve(COUNTRIES);
  }
}
