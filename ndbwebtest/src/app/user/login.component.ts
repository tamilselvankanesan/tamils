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

  constructor(private adminService: AdminService, private authService: AuthService, private router: Router) {}

  login() {
    this.message = null;
    this.adminService.login(this.appUser).subscribe(response => {
      if (response.error) {
        this.message = response.message;
      } else {
        this.authService.setToken(response.token);
        this.router.navigate(['/']); // navigate to home page
      }
    });
  }
}
