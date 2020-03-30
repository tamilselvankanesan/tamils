import { ApplicationUser } from '../../user/application-user';
import {Injectable} from '@angular/core';
import { SocialUser } from 'angularx-social-login';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import * as oauthSignature from 'oauth-signature';

@Injectable()
export class SocialAuthService {

  loggedIn = false;
  constructor(private http: HttpClient) {}

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

  getOauthTokenFromTwitter(){

    const oauth_timestamp = Math.round(new Date().getTime()/1000.0);
    const oauth_nonce = Math.round(new Date().getTime());
    const consumer_key = '';
    const consumer_secret_key = '';
    const url = 'https://api.twitter.com/oauth/request_token';
    const callback_url = 'https://localhost:5000/twitter/callback.html';
    
    const params = {
        oauth_consumer_key : consumer_key,
        oauth_nonce : oauth_nonce,
        oauth_timestamp : oauth_timestamp,
        oauth_signature_method : 'HMAC-SHA1',
        oauth_version : '1.0',
        oauth_callback: callback_url
      };

    const oauth_signature = oauthSignature.generate("POST", url, params, consumer_secret_key, '', { encodeSignature: false});
    console.log('generate signature is ', oauth_signature);
      const headers = new HttpHeaders().append('Authorization', 
      'OAuth oauth_consumer_key="'+consumer_key+'"'+
      ',oauth_timestamp="'+oauth_timestamp+'",oauth_signature_method="HMAC-SHA1",oauth_nonce="'+oauth_nonce
      +'",oauth_version="1.0",oauth_signature="'+oauth_signature+'"');

    this.http.post(url, {"oauth_callback": callback_url}, {headers}).map(
      r => console.log(r)
    ).subscribe();
  }

}
