import {AuthService} from './auth/auth.service';
import {ApplicationUser} from './user/application-user';
import {Component} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'My NDB Test workspace Home';
  searchParam: string;
  applicationUser: ApplicationUser;
  constructor(private router: Router, private authService: AuthService) {}
  search() {
    console.log(this.searchParam);
    this.router.navigateByUrl('/search/' + this.searchParam);
  }
  logout() {
    this.router.navigateByUrl('/login');
    this.authService.logout();
    console.log('logged out');
  }
  isLoggedIn(): boolean {
    return this.authService.loggedIn;
  }
}
