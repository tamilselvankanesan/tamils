import {AppRoutingModule} from './app-routing.module';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {MenubarModule} from 'primeng/menubar';
import {BreadcrumbModule} from 'primeng/breadcrumb';
import {TableModule} from 'primeng/table';
import {FieldsetModule} from 'primeng/fieldset';
import {MultiSelectModule} from 'primeng/multiselect';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {PanelModule} from 'primeng/panel';
import {ListboxModule} from 'primeng/listbox';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {DropdownModule} from 'primeng/dropdown';

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
import { JrpHomeComponent } from './jrp-home/jrp-home.component';
import { EventsComponent } from './events/events.component';
import { FilesComponent } from './files/files.component';
import { NotesComponent } from './notes/notes.component';
import { MoreActionsComponent } from './more-actions/more-actions.component';
import { ColumnSettingsService } from './services/column-settings.service';
import { AutoPacketsConfigurationComponent } from './auto-packets-configuration/auto-packets-configuration.component';


@NgModule({
  declarations: [
    AppComponent,
    PacketsComponent,
    BreadCrumbComponent,
    NavigationComponent,
    MenuComponent,
    AdvancedSearchComponent,
    CaseSearchByCaseNumberComponent,
    JrpHomeComponent,
    EventsComponent,
    FilesComponent,
    NotesComponent,
    MoreActionsComponent,
    AutoPacketsConfigurationComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, BrowserAnimationsModule, FormsModule, CommonModule, MenubarModule, BreadcrumbModule, DropdownModule,
    TableModule, FieldsetModule, MultiSelectModule, InputTextModule, ButtonModule, PanelModule, ListboxModule, OverlayPanelModule
  ],
  providers: [JrpService, BreadCrumbService, PacketsService, ColumnSettingsService],
  bootstrap: [AppComponent]
})
export class AppModule {}
