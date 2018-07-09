import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';


import {AppComponent} from './app.component';
import {MoiMainComponent} from './moi-main/moi-main.component';
import { MoiMainService } from './moi-main/moi-main.service';
import { FormsModule } from '@angular/forms';
import { MoiSearchPipe } from './moi-main/moi-search.pipe';
import { HomeComponent } from './home/home.component';
import { MoiHomeComponent } from './moi-home/moi-home.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'Moi', component: MoiMainComponent}
];


@NgModule({
  declarations: [
    AppComponent,
    MoiMainComponent,
    MoiSearchPipe,
    HomeComponent,
    MoiHomeComponent
  ],
  imports: [
    BrowserModule, FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [MoiMainService],
  bootstrap: [AppComponent]
})
export class AppModule {}
