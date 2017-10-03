import {AdminComponent} from './admin/admin/admin.component';
import {CountryListComponent} from './country/country-list.component';
import {HandsOnComponent} from './handson/hands-on/hands-on.component';
import {Routes, RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';

const appRoutes: Routes = [
  {
    path: '', component: CountryListComponent
  },
  {
    path: 'admin',
    component: AdminComponent
  },
  {
    path: 'hands-on',
    component: HandsOnComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
