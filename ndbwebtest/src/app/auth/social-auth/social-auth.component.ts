import { SocialAuthService } from './social-auth.service';
import {Component, OnInit} from '@angular/core';
import {AuthService, SocialUser, GoogleLoginProvider, FacebookLoginProvider} from 'angular4-social-login';

@Component({
  selector: 'app-social-auth',
  templateUrl: './social-auth.component.html',
  styleUrls: ['./social-auth.component.css']
})
export class SocialAuthComponent implements OnInit {

  user: SocialUser;

  constructor(private authService: AuthService, private socialAuthService: SocialAuthService) {}

  ngOnInit() {
    this.authService.authState.subscribe((user) => {
      this.user = user;
      if (this.user) {
        console.log(this.user.authToken);
      }
    });
  }

  signInWithGoogle() {
    //    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }

  signInWithFacebook() {
    this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);
    console.log('after sign IN');
  }
  signOut() {
    this.authService.signOut();
  }
}
