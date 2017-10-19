import { Base } from '../base';
import { State } from '../state/state';
export class District extends Base {
  id: number;
  name: string;
  state: State;
}
