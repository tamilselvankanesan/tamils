import {Injectable} from '@angular/core';
import {HttpHeaders} from '@angular/common/http';
import {environment} from '../environments/environment';

@Injectable()
export class BaseService {
  ndbUrl = environment.API_URL;
  headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });
  constructor() {}

  handleError(error: any): Promise<any> {
    console.log(error);
    return Promise.reject(error);
  }
}
