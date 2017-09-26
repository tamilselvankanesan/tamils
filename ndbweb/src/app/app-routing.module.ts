import {AdminComponent} from './admin/admin.component';
import {CountryComponent} from './country/country.component';
import {AddStateComponent} from './state/add-state/add-state.component';
import {StateComponent} from './state/state.component';
import {NgModule} from '@angular/core';
import {Route, RouterModule} from '@angular/router';
const routes: Route[] = [
  {
    path: 'states/:code',
    component: StateComponent,
    children: [
      {
        path: 'addState',
        component: AddStateComponent,
        outlet: 'addState1'
      }
    ]
  },
  {
    path: 'addStates',
    component: AddStateComponent
  },
  {
    path: 'countries',
    component: CountryComponent
  },
  {
    path: 'admin',
    component: AdminComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
}
)

export class AppRoutingModule {}

