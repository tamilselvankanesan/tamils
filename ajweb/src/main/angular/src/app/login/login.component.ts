import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { AuthService, GoogleLoginProvider, SocialUser } from "angularx-social-login";
import { AjbAuthService } from '../auth/ajb-auth.service';
import { User } from '../models/user';
import { PersonService } from '../user/service/person.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userName: string;
  password: string;
  message: string;
  rememberMe: boolean;
  user: SocialUser;
  constructor(private loginService: LoginService, private router: Router, private ajbAuthService: AjbAuthService, 
    private socialAuthService: AuthService, private personService: PersonService) { }

  ngOnInit() {
    //check token and navigate to dashboard page if token is valid.. TODO

    this.socialAuthService.authState.subscribe((user) => {
      this.user = user;
      console.log('social user ' + JSON.stringify(this.user));
      // this.loggedIn = (user != null);
      this.loginService.validateGoogleToken(user.idToken).subscribe(data => console.log(JSON.stringify(data)));
    });
  }
  login() {
    this.message = '';
    let user = new User();
    user.loginName = this.userName;
    user.loginPassword = this.password;

    this.loginService.login(user).subscribe(data => {
      // this.router.navigateByUrl('/dashboard/home');
      this.personService.getPersonByUserId(data.userId);
      this.router.navigate(['/dashboard']); // navigate to home page
      //navigate to home, or do the collected failed requests
      if (this.rememberMe) {
        //store the token in session storage.
        this.ajbAuthService.setTokenInLocalStorage(this.ajbAuthService.getToken(), this.userName);
      }
    },
      error => {
        this.message = error.string;
        console.log('login failed.');
      });
  }
  signInWithGoogle(): void {
    console.log('inside sigin');
    this.loginService.loginUsingGoogle();
    // this.socialAuthService.signIn(GoogleLoginProvider.PROVIDER_ID); 
  }

  signInWithLinkedIn(): void {
    console.log('inside linkedin sigin');
    this.loginService.loginUsingLinkedIn();
  }

}
