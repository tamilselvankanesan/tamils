import {AdminService} from '../admin/admin.service';
import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private adminService: AdminService) {}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('inside interceptor');
    return next.handle(this.addToken(req, next));
  }
  addToken(req: HttpRequest<any>, next: HttpHandler): HttpRequest<any> {
    let token = this.adminService.getToken();
    if (token) {
      return req.clone({setHeaders: {Authorization: token}});
    } else {
      return req;
    }
    //    return req.clone({setHeaders: {Authorization: 'testing'}});
  }
}
