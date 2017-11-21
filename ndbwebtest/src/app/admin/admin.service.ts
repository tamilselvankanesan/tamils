import {BaseService} from '../base.service';
import {Country} from '../country/country';
import {State} from '../state/state';
import {ApplicationUser} from '../user/application-user';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map';

@Injectable()
export class AdminService extends BaseService {
  private adminUrl = 'http://localhost:8080/ndb/rest/countries/import';

  constructor(private http: HttpClient) {
    super();
  }
  importCountriesToNDB(): void {
    this.http.post(this.adminUrl, {headers: this.headers}).map(error => this.handleError);
  }

  login(appUser: ApplicationUser) {
    console.log(' aa ' + appUser.applicationPassword);
    this.http.post(this.ndbUrl + 'user/login', JSON.stringify(appUser), {headers: this.headers}).map(response =>
      response as ApplicationUser, error => this.handleError).subscribe();
  }
  getCountryInfo(cCode: string): Observable<State[]> {
    //    this.http.get('http://localhost:8080/ndb/rest/countries').map(data => data as Country[]).subscribe();
    this.http.get('http://localhost:8080/ndb/rest/user/info/tamil').map(data => data as ApplicationUser).subscribe();
    return this.http.get('http://localhost:8080/ndb/rest/state/country/' + cCode).map(
      data => data as State[],
      error => super.handleError);
  }

}
