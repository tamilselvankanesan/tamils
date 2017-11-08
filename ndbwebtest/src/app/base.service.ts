import {Injectable} from '@angular/core';
import {HttpHeaders} from '@angular/common/http';

@Injectable()
export class BaseService {
  ndbUrl = 'http://localhost:8080/ndb/rest/';
  headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });
  constructor() {}

  handleError(error: any): Promise<any> {
    console.log(error);
    return Promise.reject(error);
  }
}
