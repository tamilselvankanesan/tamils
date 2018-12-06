import {User} from '../model/user';
import {AuthService} from '../service/auth.service';
import {UserService} from '../service/user.service';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService as SocialAuthService, FacebookLoginProvider} from 'angularx-social-login';
import * as $ from 'jquery';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = new User();
  username: string;
  password: string;
  error: string;

  constructor(private userService: UserService, private router: Router, private socialAuthService: SocialAuthService,
    private authService: AuthService) {} // social step 5

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

  login() {
    console.log(this.username);
    this.authService.loginUsingCredentials(this.username, this.password).subscribe(data => {
      this.authService.setLoggedInUser(data);
      this.router.navigate(['Moi-Home']);
    },
      error => {
        this.error = 'Unable to login';
      }
    );
  }

  signup() {
    this.userService.signup(this.user).subscribe(data => {
      this.user = data;
    });
    this.router.navigate(['Moi-Home']);
  }

  signInUsingFacebook() { // social step 6
    console.log('fb login => ');
    this.socialAuthService.signIn(FacebookLoginProvider.PROVIDER_ID).then(data => {
      this.authenticateUsingAuthToken(data.token);
    }, error => {console.log('error occurred while fb login');});
  }
  authenticateUsingAuthToken(token: string) {
    console.log('fb token is => ' + token);

    this.authService.validateToken(token).subscribe(data => {
      console.log('valid token => ');
      this.authService.setLoggedInUser(data);
      this.router.navigate(['Moi-Home']);
    }, error => {console.log('error occurred');});

  }
}
