import { User } from '../model/user';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';

@Injectable()
export class UserService {

  constructor() {}

  signup(user: User): Observable<User> {
    return Observable.of(user);
  }

}
