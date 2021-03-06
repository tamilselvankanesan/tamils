import { ApplicationUser } from '../../user/application-user';
import {Injectable} from '@angular/core';
import { SocialUser } from 'angularx-social-login';

@Injectable()
export class SocialAuthService {

  loggedIn = false;
  constructor() {}

  isAuthenticated(): boolean {
    return true;
  }
  getToken(): string {
    console.log('inside auth service - ndbtoken->' + localStorage.getItem('ndbtoken'));
//    if (this.isAuthenticated()) {
//      console.log('token not found. forward to login page..');
//    }
    //    console.log('toekn => '+this.jwtHelper.decodeToken(localStorage.getItem('ndbtoken')));
    return localStorage.getItem('ndbsocialtoken');
  }
  setToken(socialUser: SocialUser) {
    console.log('inside auth service - settotkem->');
//    localStorage.setItem('ndbsocialtoken', 'Bearer ' + token);
  }
  setNdbToken(appUser: ApplicationUser) {
    console.log('inside auth service - settotkem->');
//    localStorage.setItem('ndbsocialtoken', 'Bearer ' + token);
  }
}
