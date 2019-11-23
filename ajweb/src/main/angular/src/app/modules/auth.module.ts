import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AjbAuthService } from '../auth/ajb-auth.service';
import { LoginService } from '../login/login.service';
import { GoogleLoginProvider, AuthServiceConfig, SocialLoginModule } from "angularx-social-login";
import { SharedModule } from '../shared/shared.module';
import { LinkedInCallBack } from '../auth/linkedin-callback.component';
import { GoogleCallbackComponent } from '../auth/google-callback.component';

export const AuthRoutes: Routes = [{
  path: '',
  children: [{
    path: '',
    component: LoginComponent
  },
  {
    path: 'callback',
    component: LinkedInCallBack
  },
  {
    path: 'google/callback',
    component: GoogleCallbackComponent
  }
]
}];

let config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider('336119533592-n24lvu4trdacd3b0iaj7b6c9ge9d9kb6.apps.googleusercontent.com')
  }
]);

export function provideConfig() {
  return config;
}

@NgModule({
  declarations: [LoginComponent, LinkedInCallBack, GoogleCallbackComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(AuthRoutes),
    ReactiveFormsModule, FormsModule, HttpClientModule, SocialLoginModule, SharedModule
  ],
  providers: [AjbAuthService, LoginService,
    {
      provide: AuthServiceConfig,
      useFactory: provideConfig
    }
  ]
})
export class AuthModule { }
