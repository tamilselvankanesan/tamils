import { NavigationComponent } from './navigation/navigation.component';
import {PacketsComponent} from './packets/packets.component';
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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
