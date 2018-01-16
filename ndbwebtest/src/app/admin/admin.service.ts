import {BaseService} from '../base.service';
import {Country} from '../country/country';
import {JwtAuthenticationResponse} from '../jwt';
import {State} from '../state/state';
import {ApplicationUser} from '../user/application-user';
import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map';

@Injectable()
export class AdminService extends BaseService {
  private adminUrl = 'http://localhost:8080/ndb/rest/countries/import';
  private failedRequests: Array<HttpRequest<any>> = [];

  constructor(private http: HttpClient) {
    super();
  }
  importCountriesToNDB(): void {
    this.http.post(this.adminUrl, {headers: this.headers}).map(error => this.handleError);
  }

  login1(appUser: ApplicationUser) {
    console.log(' aa ' + appUser.applicationPassword);
    this.http.post(this.ndbUrl + 'user/login', JSON.stringify(appUser), {headers: this.headers}).map(
      data => data as JwtAuthenticationResponse, error => this.handleError).subscribe(
      data => {
        console.log('data.token==>' + data.token);
        localStorage.setItem('ndbtoken', 'Bearer ' + data.token);
      }
      );
  }

  login(appUser: ApplicationUser): Observable<JwtAuthenticationResponse> {
    console.log('inside login1');
    return this.http.post(this.ndbUrl + 'user/login', JSON.stringify(appUser), {headers: this.headers}).map(data => data as JwtAuthenticationResponse,
      error => this.handleError);
  }


  getCountryInfo(cCode: string): Observable<State[]> {
    //    this.http.get('http://localhost:8080/ndb/rest/countries').map(data => data as Country[]).subscribe();
    this.http.get('http://localhost:8080/ndb/rest/user/info/tamil').map(data => data as ApplicationUser).subscribe();
    return this.http.get('http://localhost:8080/ndb/rest/state/country/' + cCode).map(
      data => data as State[],
      error => super.handleError);
  }
}
