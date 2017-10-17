import {AdminComponent} from './admin/admin.component';
import { AddCityComponent } from './city/add-city.component';
import {CountryListComponent} from './country/country-list.component';
import { AddDistrictComponent } from './district/add-district.component';
import {HandsOnComponent} from './handson/hands-on/hands-on.component';
import { AddStateComponent } from './state/add-state.component';
import {Routes, RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';

const appRoutes: Routes = [
  {
    path: '', component: CountryListComponent
  },
  {
    path: 'admin',
    component: AdminComponent,
    children: [{
        path: 'add-state',
        component: AddStateComponent
      },
      {
        path: 'add-city',
        component: AddCityComponent
      },
      {
        path: 'add-district',
        component: AddDistrictComponent
      }
    ]
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
