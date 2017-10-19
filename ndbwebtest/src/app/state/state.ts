import {Base} from '../base';
import {Country} from '../country/country';
export class State extends Base {
  id: number;
  code: string;
  name: string;
  country: Country;
}
