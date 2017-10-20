import {Base} from '../base';
import {District} from '../district/district';
import {State} from '../state/state';
export class City extends Base {
  id: number;
  name: string;
  district: District;
}
