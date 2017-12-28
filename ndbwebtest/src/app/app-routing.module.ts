import {AdminComponent} from './admin/admin.component';
import {AddCityComponent} from './city/add-city.component';
import {CountryListComponent} from './country/country-list.component';
import {AddDistrictComponent} from './district/add-district.component';
import {HandsOnComponent} from './handson/hands-on/hands-on.component';
import {AddPersonComponent} from './person/add-person.component';
import {PersonDetailComponent} from './person/person-detail.component';
import {PersonSearchComponent} from './person/person-search.component';
import {AddStateComponent} from './state/add-state.component';
import { LoginComponent } from './user/login.component';
import {Routes, RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';

const appRoutes: Routes = [
  {
    path: 'login', component: LoginComponent
  },
  {
    path: '', component: PersonSearchComponent
  },
  {
    path: 'search/:param', component: PersonSearchComponent
  },
  {
    path: 'person/:id', component: PersonDetailComponent
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
    },
    {
      path: 'add-person',
      component: AddPersonComponent
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
