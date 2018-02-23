import {BaseService} from '../base.service';
import {HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {tokenNotExpired, JwtHelper} from 'angular2-jwt';
@Injectable()
export class AuthService extends BaseService {
  private failedRequests: Array<HttpRequest<any>> = [];
  loggedIn = false;
  jwtHelper: JwtHelper = new JwtHelper();

  getToken(): string {
    console.log('inside auth service - ndbtoken->' + localStorage.getItem('ndbtoken'));
    if (this.isAuthenticated()) {
      console.log('token not found. forward to login page..');
    }
    //    console.log('toekn => '+this.jwtHelper.decodeToken(localStorage.getItem('ndbtoken')));
    return localStorage.getItem('ndbtoken');
  }
  setToken(token) {
    console.log('inside auth service - settotkem->');
    localStorage.setItem('ndbtoken', 'Bearer ' + token);
  }
  isAuthenticated(): boolean {
    return tokenNotExpired(localStorage.getItem('ndbtoken'));
  }
  collectFailedRequests(failedHttpRequest: HttpRequest<any>) {
    this.failedRequests = [];
    this.failedRequests.push(failedHttpRequest);
  }
  logout() {
    localStorage.removeItem('ndbtoken');
    this.loggedIn = false;
  }
}
