import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';


import {AppComponent} from './app.component';
import {MoiMainComponent} from './moi-main/moi-main.component';
import { MoiMainService } from './moi-main/moi-main.service';
import { FormsModule } from '@angular/forms';
import { MoiSearchPipe } from './moi-main/moi-search.pipe';
import { HomeComponent } from './home/home.component';
import { PasswordValidator } from './home/password_validator';
import { MoiHomeComponent } from './moi-home/moi-home.component';
import { UserService } from './service/user.service';

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
    BrowserModule, FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [MoiMainService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule {}
