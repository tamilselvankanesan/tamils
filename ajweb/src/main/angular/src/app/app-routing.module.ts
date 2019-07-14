import { Routes } from '@angular/router';
import { AuthLayoutComponent } from './layouts/auth/auth-layout.component';
import { MainLayoutComponent } from './layouts/main/main-layout.component';
import { AuthGuardService } from './auth/auth-guard.service';

export const AppRoutes: Routes = [{
  path: '',
  redirectTo: 'login',
  pathMatch: 'full'
},
{
  path: '',
  component: AuthLayoutComponent,
  children: [{
    path: 'login',
    loadChildren: './modules/auth.module#AuthModule'
  }]
},
{
  path: '',
  component: MainLayoutComponent,
  canActivate: [AuthGuardService],
  children: [{
    path: 'dashboard',
    loadChildren: './modules/dashboard.module#DashboardModule'
  },
  {
    path: 'user',
    loadChildren: './modules/user.module#UserModule'
  }
]
}
];