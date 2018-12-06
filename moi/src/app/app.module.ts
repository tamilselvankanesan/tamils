import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {SocialLoginModule, AuthServiceConfig} from 'angularx-social-login'; // social step 1
import {getAuthServiceConfigs} from './socialloginConfig';

import {AppComponent} from './app.component';
import {MoiMainComponent} from './moi-main/moi-main.component';
import {HomeComponent} from './home/home.component';
import {MoiHomeComponent} from './moi-home/moi-home.component';
import { LoginComponent } from './login/login.component';

import {MoiSearchPipe} from './util/pipe/moi-search.pipe';
import {PasswordValidator} from './util/validator/password_validator';

import {MoiMainService} from './service/moi-main.service';
import {AuthGuradService} from './service/auth-gurad.service';
import {AuthService} from './service/auth.service';
import {UserService} from './service/user.service';

const routes: Routes = [
  {
    path: 'Login', component: LoginComponent
  },
  {
    path: 'Moi-Home', component: MoiHomeComponent
  },
  {
    path: '', component: LoginComponent
  },
  {
    path: 'Moi-Main', component: MoiMainComponent
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
    HttpClientModule, RouterModule.forRoot(routes)
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
