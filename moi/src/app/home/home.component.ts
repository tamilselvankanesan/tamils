import {UserService} from '../service/user.service';
import {User} from './user';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService as SocialAuthService, FacebookLoginProvider} from 'angular5-social-login';
import * as $ from 'jquery';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user = new User();

  constructor(private userService: UserService, private router: Router, private socialAuthService: SocialAuthService) {} // social step 5

  ngOnInit() {
  }
  toggleSignupAndLogin(flag: boolean) {
    if (flag) {
      $('#loginbox').hide();
      $('#signupbox').show();
    } else {
      $('#signupbox').hide();
      $('#loginbox').show();
    }
  }

  signup() {
    this.userService.signup(this.user).subscribe(data => {
      this.user = data;
    });
    this.router.navigate(['Moi-Home']);
  }

  signupUsingFacebook() { // social step 6
    console.log('fb login => ');
    this.socialAuthService.signIn(FacebookLoginProvider.PROVIDER_ID).then(data => {
      this.authenticateUsingAuthToken(data.token);
    });
  }
  authenticateUsingAuthToken(token: string) {
    console.log('fb token is => ' + token);
  }
}
