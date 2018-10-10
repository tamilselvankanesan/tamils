import { BreadCrumbService } from './bread-crumb.service';
import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-bread-crumb',
  templateUrl: './bread-crumb.component.html',
  styleUrls: ['./bread-crumb.component.css']
})
export class BreadCrumbComponent {

  breadcrumb: MenuItem[];
  constructor(private breadcrumbService: BreadCrumbService) {
    this.breadcrumbService.breadCrumbs$.subscribe(breadcrumb => { this.breadcrumb = breadcrumb; });
  }
}
