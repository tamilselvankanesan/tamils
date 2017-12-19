import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('inside interceptor');
    return next.handle(this.addToken(req, next));
  }
  addToken(req: HttpRequest<any>, next: HttpHandler): HttpRequest<any> {
    console.log(localStorage.getItem('ndbtoken'));
    return req.clone({setHeaders: {Authorization: localStorage.getItem('ndbtoken')}});
//    return req.clone({setHeaders: {Authorization: 'testing'}});
  }
}
