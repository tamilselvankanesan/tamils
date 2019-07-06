import { AjbAuthService } from './ajb-auth.service';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import 'rxjs/add/operator/do';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private authService: AjbAuthService, private router: Router) { }
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = this.addToken(req);

    return next.handle(req).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
        //200 successful.. no action needed
        //refresh token
        this.authService.setToken(event.headers.get('Authorization'));
      }
    }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        console.error('error occurred.. error code is '+err.status);
        if (err.status === 401) {
          this.authService.collectFailedRequests(req); //TODO
          this.authService.logout();
          this.router.navigate(['/login']);
        }
      }
    });
  }
  addToken(req: HttpRequest<any>): HttpRequest<any> {
    if(!req.url.includes('user/login')){
      let token = this.authService.getToken();
      if (token) {
        return req.clone({ setHeaders: { Authorization: token } });
      } else {
        return req;
      }
    }else{
      return req;
    }
  }
}
