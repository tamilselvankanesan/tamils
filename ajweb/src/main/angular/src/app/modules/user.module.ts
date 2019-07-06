import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { AccountComponent } from '../user/account/account.component';
import { PublicCardComponent } from '../user/public-card/public-card.component';
import { VerifiedInfoComponent } from '../user/verified-info/verified-info.component';
import { SettingsComponent } from '../user/settings/settings.component';
import { ResumeComponent } from '../user/resume/resume.component';
import { ProfessionalInfoComponent } from '../user/professional-info/professional-info.component';
import { DemographicsComponent } from '../user/demographics/demographics.component';
import { ExperienceComponent } from '../user/experience/experience.component';
import { PersonalInfoComponent } from '../user/personal-info/personal-info.component';
import { DropdownModule } from 'primeng/dropdown';
import {CheckboxModule} from 'primeng/checkbox';
import { PersonService } from '../user/service/person.service';

export const MainRoutes: Routes = [{
  path: '',
  children: [
    {
      path: 'accounts',
      component: AccountComponent
    }
  ]
}];

@NgModule({
  declarations: [
    AccountComponent, PersonalInfoComponent,
    ExperienceComponent, DemographicsComponent, ProfessionalInfoComponent, ResumeComponent, SettingsComponent, VerifiedInfoComponent, PublicCardComponent
  ],
  imports: [
    CommonModule, RouterModule.forChild(MainRoutes), DropdownModule, CheckboxModule
  ], providers: []
})
export class UserModule { }
