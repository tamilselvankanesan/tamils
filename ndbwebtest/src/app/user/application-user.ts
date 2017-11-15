import {Base} from '../base';
export class ApplicationUser extends Base {
  firstName: string;
  lastName: string;
  applicationLogin: string;
  applicationPassword: string;
  userId: number;
}
