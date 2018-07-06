import {AppRoutingModule} from './app-routing.module';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {MenubarModule} from 'primeng/menubar';
import {BreadcrumbModule} from 'primeng/breadcrumb';
import {TableModule} from 'primeng/table';
import {FieldsetModule} from 'primeng/fieldset';
import {CardModule} from 'primeng/card';

import {AppComponent} from './app.component';
import {PacketsComponent} from './packets/packets.component';
import {BreadCrumbComponent} from './bread-crumb/bread-crumb.component';
import {BreadCrumbService} from './bread-crumb/bread-crumb.service';
import {NavigationComponent} from './navigation/navigation.component';
import {JrpService} from './services/jrp.service';
import {FormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MenuComponent} from './menu/menu.component';
import {PacketsService} from './packets/packets.service';
import { AdvancedSearchComponent } from './search/advanced-search/advanced-search.component';
import { CaseSearchByCaseNumberComponent } from './components/case-search-by-case-number/case-search-by-case-number.component';


@NgModule({
  declarations: [
    AppComponent,
    PacketsComponent,
    BreadCrumbComponent,
    NavigationComponent,
    MenuComponent,
    AdvancedSearchComponent,
    CaseSearchByCaseNumberComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, BrowserAnimationsModule, CommonModule, MenubarModule, BreadcrumbModule, 
    TableModule, FieldsetModule, CardModule
  ],
  providers: [JrpService, BreadCrumbService, PacketsService],
  bootstrap: [AppComponent]
})
export class AppModule {}
