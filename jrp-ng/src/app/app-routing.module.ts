import { NavigationComponent } from './navigation/navigation.component';
import { AdvancedSearchComponent } from './search/advanced-search/advanced-search.component';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { JrpHomeComponent } from './jrp-home/jrp-home.component';
import { AutoPacketsConfigurationComponent } from './auto-packets-configuration/auto-packets-configuration.component';

const routes: Routes = [
  {
    path: '', component: NavigationComponent
  },
  {
    path: 'inbox/:id',
    component: NavigationComponent
  },
  {
    path: 'search/:id',
    component: NavigationComponent
  },
  {
    path: 'home',
    component: JrpHomeComponent
  },
  {
    path: 'AdvancedSearch',
    component: AdvancedSearchComponent
  },
  {
    path: 'configuration/:id',
    component: NavigationComponent
  },
  {
    path: 'AutoPacketsConfiguration',
    component: AutoPacketsConfigurationComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  // imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
