import {BaseService} from '../base.service';
import {HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {tokenNotExpired} from 'angular2-jwt';
@Injectable()
export class AuthService extends BaseService {
  private failedRequests: Array<HttpRequest<any>> = [];

  getToken(): string {
    console.log('inside auth service - ndbtoken->' + localStorage.getItem('ndbtoken'));
    if (tokenNotExpired(null, localStorage.getItem('ndbtoken'))) {
      console.log('token not found. forward to login page..');
    }
    return localStorage.getItem('ndbtoken');
  }
  setToken(token) {
    console.log('inside auth service - settotkem->');
    localStorage.setItem('ndbtoken', 'Bearer ' + token);
  }
  collectFailedRequests(failedHttpRequest: HttpRequest<any>) {
    this.failedRequests.push(failedHttpRequest);
  }
}
