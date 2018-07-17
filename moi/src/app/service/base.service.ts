import {environment} from '../../environments/environment';
import {HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable()
export class BaseService {
  moiUrl = environment.API_URL;
  headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });
  constructor() {}

  handleError(error: any): Promise<any> {
    console.log(error);
    return Promise.reject(error);
  }

}
