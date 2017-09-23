import { AdminComponent } from './admin/admin.component';
import {CountryComponent} from './country/country.component';
import {StateComponent} from './state/state.component';
import { NgModule } from '@angular/core';
import {Route, RouterModule} from '@angular/router';
const routes: Route[] = [
  {
    path: 'states/:code',
    component: StateComponent
  },
  {
    path: 'countries',
    component: CountryComponent
  },
  {
    path: 'admin',
    component: AdminComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
}
)

export class AppRoutingModule {}

