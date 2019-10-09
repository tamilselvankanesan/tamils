import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs';
import 'rxjs/add/observable/of';
import { BaseService } from '../base.service';
import {HttpClient, HttpParams} from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService extends BaseService{

  constructor(private http: HttpClient) {
    super();
   }

  login(user): Observable<User>{
    return this.http.post(this.baseUrl+'user/login', JSON.stringify(user), {headers: this.headers}).map(data => data);
  }

  loginUsingLinkedIn(){
    const state = Math.random().toString(36).substr(2,20).toString(); 
    sessionStorage.setItem('linkedin_state', state);
    const  params = new  HttpParams().set('response_type', "code").
    set('client_id', environment.LINKED_IN_CLIENT_ID).
    set('redirect_uri', location.origin+'/login/callback').
    set('state', state).
    set('scope', environment.LINKED_IN_SCOPE);
    params.toString()
    console.log('before calling linked in lllll.. '+params.toString());
    window.location.href = environment.LINKED_IN_URL+'?'+params.toString();
  }

  loginUsingGoogle(){
    const state = Math.random().toString(36).substr(2,20).toString(); 
    sessionStorage.setItem('google_state', state);
    const  params = new  HttpParams().set('access_type', "online").
    set('client_id', environment.GOOGLE_CLIENT_ID).
    set('redirect_uri', location.origin+'/login/google/callback').
    set('state', state).
    set('response_type', 'code').
    set('scope', environment.GOOGLE_SCOPE);
    params.toString()
    console.log('before calling google in lllll.. '+params.toString());
    window.location.href = environment.GOOGLE_URL+'?'+params.toString();
  }

  validateLinkedInToken(code): Observable<User>{
    console.log('validate linkedin token...');
    let body: HttpParams = new HttpParams();
    body = body.append('code', code).append('redirect_uri', location.origin+'/login/callback');
    return this.http.post(this.baseUrl+'login-with-linkedin', body).map(data => data);
  }

  validateGoogleToken(idToken){
    console.log('validate google token...');
    let body: HttpParams = new HttpParams();
    body = body.append('idToken', idToken);
    return this.http.post(this.baseUrl+'login-with-google', body).map(data => data);
  }
}
