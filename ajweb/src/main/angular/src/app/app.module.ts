import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';

import { AppRoutes } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './auth/token.interceptor';
import { AuthLayoutComponent } from './layouts/auth/auth-layout.component';
import { MainLayoutComponent } from './layouts/main/main-layout.component';
import { SharedModule } from './shared/shared.module';
import { LeftNavComponent } from './left-nav/left-nav.component';
import { AuthGuardService } from './auth/auth-guard.service';
import { AjbAuthService } from './auth/ajb-auth.service';

@NgModule({
  declarations: [
    AppComponent,
    AuthLayoutComponent,
    MainLayoutComponent,
    LeftNavComponent
  ],
  imports: [
    BrowserModule, RouterModule.forRoot(AppRoutes), SharedModule, BrowserAnimationsModule
  ],
  providers: [ 
    AuthGuardService, AjbAuthService,
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
