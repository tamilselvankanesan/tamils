import {AdminService} from '../admin/admin.service';
import { AuthService } from '../auth/auth.service';
import {JwtAuthenticationResponse} from '../jwt';
import {ApplicationUser} from './application-user';
import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  userName: string;
  password: string;
  message: string;
  appUser = new ApplicationUser();
  success = true;

  constructor(private adminService: AdminService, private authService: AuthService, private router: Router) {}

  login() {
    this.message = null;
    this.adminService.login(this.appUser).subscribe(response => {
      if (response.error) {
        this.message = response.message;
        this.success = false;
      } else {
        this.authService.setToken(response.token);
        this.authService.loggedIn = true;
        // perform queued requests if any, otherwise go to home page
        this.router.navigate(['/']); // navigate to home page
      }
    });
  }
}
