import { User } from './model/user';
import {AuthService} from './service/auth.service';
import {Component} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Moi';
  user: User;

  constructor(private authService: AuthService, private router: Router) {
    this.authService.loggedInUser.subscribe(user => {
      this.user = user;
    });
  }
  logout() {
    this.authService.logout();
    this.router.navigate(['']);
  }
}
