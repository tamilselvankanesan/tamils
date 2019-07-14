import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import {HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BaseService {
  baseUrl = environment.API_URL;
  headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });
  constructor() { }
  
  handleError(error: any): Promise<any> {
    console.log('error occurred. '+error);
    return Promise.reject(error);
  }
}
