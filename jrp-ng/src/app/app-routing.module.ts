import { NavigationComponent } from './navigation/navigation.component';
import {PacketsComponent} from './packets/packets.component';
import { AdvancedSearchComponent } from './search/advanced-search/advanced-search.component';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

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
    path: 'packets',
    component: PacketsComponent
  },
  {
    path: 'AdvancedSearch',
    component: AdvancedSearchComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
