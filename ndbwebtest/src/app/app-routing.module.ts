import {CountryListComponent} from './country/country-list.component';
import {Routes, RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';

const appRoutes: Routes = [
  {
    path: '', component: CountryListComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
