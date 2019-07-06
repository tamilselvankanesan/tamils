import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import { AjbAuthService } from './ajb-auth.service';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private authService: AjbAuthService, private router: Router) {}

  canActivate(): boolean {
    console.log('check token presence');
    // if (!this.authService.tokenExists()) {
    //   console.log(' token not found ');
    //   this.router.navigate(['/login']);
    //   return false;
    // }else{
    //   console.log(' token found ');
    //   return true;
    // }
    return true;
  }
}
