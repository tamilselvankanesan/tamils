import {AdminService} from '../../admin/admin.service';
import {ApplicationUser} from '../../user/application-user';
import {SocialAuthService} from './social-auth.service';
import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService, SocialUser, GoogleLoginProvider, FacebookLoginProvider} from 'angularx-social-login';

@Component({
  selector: 'app-social-auth',
  templateUrl: './social-auth.component.html',
  styleUrls: ['./social-auth.component.css']
})
export class SocialAuthComponent implements OnInit {

  user = new SocialUser();
  appUser = new ApplicationUser();
  message: string;
  success = true;
  constructor(private authService: AuthService, private socialAuthService: SocialAuthService,
    private adminService: AdminService, private router: Router) {}

  ngOnInit() {
    this.authService.authState.subscribe((user) => {
      this.user = user;
      if (this.user) {
        console.log(this.user.authToken);
        this.setToken(this.user.authToken);
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
  ndbLogin() {
    this.message = null;
    this.adminService.login(this.appUser).subscribe(response => {
      if (response.error) {
        console.log('auth error');
        this.message = response.message;
        this.success = false;
      } else {
        console.log('auth success'+ response.token);
//        this.user.authToken = response.token;
        console.log('auth success 11 1');
        this.setToken(response.token);
        //        this.socialAuthService.setToken(this.user);
        //        this.socialAuthService.loggedIn = true;
        // perform queued requests if any, otherwise go to home page
        //        this.router.navigate(['/']); // navigate to home page
      }
    });
  }
  setToken(token: string) {
    this.appUser.token = token;
    this.socialAuthService.setNdbToken(this.appUser);
    this.socialAuthService.loggedIn = true;
    // perform queued requests if any, otherwise go to home page
    this.router.navigate(['/']); // navigate to home page
  }
}
