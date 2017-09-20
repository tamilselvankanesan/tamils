import {State} from './state';
import {Injectable} from '@angular/core';
const STATES: State[] = [
  {id: 1, name: 'Tamil Nadu', country_id: 1},
  {id: 2, name: 'Andhra Pradesh', country_id: 2},
  {id: 3, name: 'Karnataka', country_id: 3},
  {id: 4, name: 'Kerala', country_id: 4}
];
@Injectable()
export class StateService {

  constructor() {}
  getAllStates(): Promise<State[]> {
    return Promise.resolve(STATES);
  }
  getStates(id: number): Promise<State[]> {
    return this.getAllStates().then(states => states.filter(state => state.country_id === id));
  }
}
