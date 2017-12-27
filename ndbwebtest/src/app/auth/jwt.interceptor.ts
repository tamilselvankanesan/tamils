import {AdminService} from '../admin/admin.service';
import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
@Injectable()
export class JWTInterceptor implements HttpInterceptor {
  constructor(private adminService: AdminService) {}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
        // response OK. do nothing
      }

    }, (err: any) => {
      if (err instanceof HttpErrorResponse && err.status === 401) {
        this.adminService.collectFailedRequests(req);
        // redirect to login page
      }
    });
  }
}
