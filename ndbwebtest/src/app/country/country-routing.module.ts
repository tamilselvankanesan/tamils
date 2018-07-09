import { CountryDetailComponent } from './country-detail.component';
import {NgModule} from '@angular/core';
import {CountryListComponent} from './country-list.component';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: 'countries',
    component: CountryListComponent
  },
  {
    path: 'country/:code',
    component: CountryDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class CountryRoutingModule {}
