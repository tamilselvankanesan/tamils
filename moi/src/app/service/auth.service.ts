import {User} from '../model/user';
import {Injectable, EventEmitter} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';

@Injectable()
export class AuthService {

  static MOI_TOKEN = 'MOI_TOKEN';
  public loggedInUser: EventEmitter<User> = new EventEmitter<User>();

  constructor() {}

  setLoggedInUser(user: User) {
    this.loggedInUser.next(user);
  }

  loginUsingCredentials(username: string, password: string): Observable<User> {
    let user = new User();
    user.firstName = username;
    user.lastName = username;
    user.email = 'ss@ss.com';
    this.setToken(username);
    return Observable.of(user);
  }

  validateToken(token: string): Observable<User> {
    let user = new User();
    user.firstName = 'Tamilselvan';
    user.lastName = 'Balasubramaniam';
    user.email = 'ss@ss.com';
    this.setToken(token);
    return Observable.of(user);
  }
  isValidToken(): boolean {
    return false;
  }

  setToken(token: string) {
    localStorage.setItem(AuthService.MOI_TOKEN, 'Bearer ' + token);
  }

  isAuthenticated(): boolean {
    console.log('if authenticated....');
    if (localStorage.getItem(AuthService.MOI_TOKEN)) {
      return true;
    }
    return false;
  }
  logout() {
    this.removeToken();
  }
  removeToken() {
    localStorage.removeItem(AuthService.MOI_TOKEN);
  }

}
