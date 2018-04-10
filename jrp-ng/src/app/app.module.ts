import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { PacketsComponent } from './packets/packets.component';
import { BreadCrumbComponent } from './bread-crumb/bread-crumb.component';
import { NavigationComponent } from './navigation/navigation.component';
import { MatMenuModule } from '@angular/material/menu';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


@NgModule({
  declarations: [
    AppComponent,
    PacketsComponent,
    BreadCrumbComponent,
    NavigationComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, MatMenuModule, BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
