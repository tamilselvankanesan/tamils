import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {SocialLoginModule, FacebookLoginProvider, AuthServiceConfig} from 'angular5-social-login'; // social step 1
import {getAuthServiceConfigs} from './socialloginConfig';

import {AppComponent} from './app.component';
import {MoiMainComponent} from './moi-main/moi-main.component';
import {MoiMainService} from './service/moi-main.service';
import {FormsModule} from '@angular/forms';
import {MoiSearchPipe} from './util/pipe/moi-search.pipe';
import {HomeComponent} from './home/home.component';
import {PasswordValidator} from './util/validator/password_validator';
import {MoiHomeComponent} from './moi-home/moi-home.component';
import {AuthGuradService} from './service/auth-gurad.service';
import {AuthService} from './service/auth.service';
import {UserService} from './service/user.service';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {
    path: 'Login', component: LoginComponent
  },
  {
    path: 'Moi-Home', component: MoiHomeComponent, canActivate: [AuthGuradService]
  },
  {
    path: '', component: LoginComponent
  },
  {
    path: 'Moi-Main', component: MoiMainComponent, canActivate: [AuthGuradService]
  }
  //  ,
  //  {
  //    path: '', canActivateChild: [AuthGuradService], children: [
  //      {path: 'Moi', component: MoiMainComponent},
  //      {path: 'Moi-Home', component: MoiHomeComponent}
  //    ]
  //  }
];

@NgModule({
  declarations: [
    AppComponent,
    MoiMainComponent,
    MoiSearchPipe,
    HomeComponent,
    MoiHomeComponent,
    PasswordValidator,
    LoginComponent
  ],
  imports: [
    BrowserModule, FormsModule, SocialLoginModule, // social step 2
    RouterModule.forRoot(routes)
  ],
  providers: [
    {provide: MoiMainService, useClass: MoiMainService},
    {provide: AuthGuradService, useClass: AuthGuradService},
    {provide: UserService, useClass: UserService},
    {provide: AuthService, useClass: AuthService},
    {provide: AuthServiceConfig, useFactory: getAuthServiceConfigs} // social step 4
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
