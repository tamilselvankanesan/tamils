import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from '../dashboard/dashboard.component';

export const MainRoutes: Routes = [{
  path: '',
  children: [{
    path: '',
    component: DashboardComponent
  }
  ]
}];

@NgModule({
  declarations: [DashboardComponent],
  imports: [
    CommonModule, RouterModule.forChild(MainRoutes)
  ]
})
export class DashboardModule { }
