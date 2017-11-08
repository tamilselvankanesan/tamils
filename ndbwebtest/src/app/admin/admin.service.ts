import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class AdminService {
  private adminUrl = 'http://localhost:8080/ndb/rest/countries/import';
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) {}
  importCountriesToNDB(): void {
    this.http.post(this.adminUrl, {headers: this.headers}).toPromise().catch(this.handleError);
  }
  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
