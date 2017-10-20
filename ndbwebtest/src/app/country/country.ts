import {Base} from '../base';
import {State} from '../state/state';
export class Country extends Base {
  code: string;
  name: string;
  state: State[];

  constructor(code: string, name: string, state: State[]) {
    super();
    this.code = code;
    this.name = name;
    this.state = state;
  }
}
