import {BaseService} from '../base.service';
import {HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
@Injectable()
export class AjbAuthService extends BaseService {
  private failedRequests: Array<HttpRequest<any>> = [];//TODO
  tokenName = 'ajtoken';
  ajbUserName = 'ajUserName';
  getToken(): string {
    return sessionStorage.getItem(this.tokenName);
  }
  setToken(token) {
    sessionStorage.setItem(this.tokenName, token);
  }
  setTokenInLocalStorage(token, userName) {
    localStorage.setItem(this.tokenName, token);
    localStorage.setItem(this.ajbUserName, userName);
  }
  collectFailedRequests(failedHttpRequest: HttpRequest<any>) {
    this.failedRequests = [];
    this.failedRequests.push(failedHttpRequest);
  }
  logout() {
    this.removeToken();
    //navigate to login page -TODO
  }
  removeToken() {
    sessionStorage.removeItem(this.tokenName);
  }
  tokenExists(): boolean {
    console.log('token is '+this.getToken());
    let token =  this.getToken();
    if(token && token !== ''){
      return true;
    }else{
      return false;
    }
  }
}
