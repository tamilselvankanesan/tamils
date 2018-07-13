import {AuthService} from './auth.service';
import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';

@Injectable()
export class AuthGuradService implements CanActivate {
  canActivate(): boolean {
    let authenticated = this.authService.isAuthenticated();
    if (authenticated) {
      return true;
    } else {
      this.router.navigate(['Login']);
    }
    return false;
  }

  constructor(private authService: AuthService, private router: Router) {}

}
