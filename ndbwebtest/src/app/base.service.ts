import {Injectable} from '@angular/core';
import {Headers} from '@angular/http';

@Injectable()
export class BaseService {
  ndbUrl = 'http://localhost:8080/ndb/rest/';
  headers = new Headers({
    'Content-Type': 'application/json'
  });
  constructor() {}

  handleError(error: any): Promise<any> {
    console.log(error);
    return Promise.reject(error);
  }
}
