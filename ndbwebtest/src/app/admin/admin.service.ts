import {BaseService} from '../base.service';
import {ApplicationUser} from '../user/application-user';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Injectable()
export class AdminService extends BaseService {
  private adminUrl = 'http://localhost:8080/ndb/rest/countries/import';
  //  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) {
    super();
  }
  importCountriesToNDB(): void {
    this.http.post(this.adminUrl, {headers: this.headers}).toPromise().catch(this.handleError);
  }

  login(appUser: ApplicationUser) {
    console.log(' aa ' + appUser.applicationPassword);
    this.http.post(this.ndbUrl + 'user/login', JSON.stringify(appUser), {headers: this.headers}).map(response =>
      response as ApplicationUser, error => this.handleError);
  }
}
