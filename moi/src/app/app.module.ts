import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';


import {AppComponent} from './app.component';
import {MoiMainComponent} from './moi-main/moi-main.component';
import { MoiMainService } from './moi-main/moi-main.service';
import { FormsModule } from '@angular/forms';
import { MoiSearchPipe } from './moi-main/moi-search.pipe';

const routes: Routes = [
  {path: '', component: MoiMainComponent}
];


@NgModule({
  declarations: [
    AppComponent,
    MoiMainComponent,
    MoiSearchPipe
  ],
  imports: [
    BrowserModule, FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [MoiMainService],
  bootstrap: [AppComponent]
})
export class AppModule {}
