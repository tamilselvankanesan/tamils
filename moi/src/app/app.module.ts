import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {SocialLoginModule, FacebookLoginProvider, AuthServiceConfig} from 'angular5-social-login'; // social step 1
import {getAuthServiceConfigs} from './socialloginConfig';

import {AppComponent} from './app.component';
import {MoiMainComponent} from './moi-main/moi-main.component';
import {MoiMainService} from './moi-main/moi-main.service';
import {FormsModule} from '@angular/forms';
import {MoiSearchPipe} from './moi-main/moi-search.pipe';
import {HomeComponent} from './home/home.component';
import {PasswordValidator} from './home/password_validator';
import {MoiHomeComponent} from './moi-home/moi-home.component';
import {UserService} from './service/user.service';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'Moi', component: MoiMainComponent},
  {path: 'Moi-Home', component: MoiHomeComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    MoiMainComponent,
    MoiSearchPipe,
    HomeComponent,
    MoiHomeComponent,
    PasswordValidator
  ],
  imports: [
    BrowserModule, FormsModule, SocialLoginModule, // social step 2
    RouterModule.forRoot(routes)
  ],
  providers: [
    {provide: MoiMainService, useClass: MoiMainService},
    {provide: UserService, useClass: UserService},
    {provide: AuthServiceConfig, useFactory: getAuthServiceConfigs} // social step 4
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
