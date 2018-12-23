import {User} from '../model/user';
import {BaseService} from './base.service';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import 'rxjs/add/observable/of';


@Injectable()
export class UserService extends BaseService {

  constructor() {
    super();
  }

  signup(user: User): Observable<User> {
    return Observable.of(user);
  }

}
