import {BreadCrumbService} from './bread-crumb.service';
import {Component} from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-bread-crumb',
  templateUrl: './bread-crumb.component.html',
  styleUrls: ['./bread-crumb.component.css']
})
export class BreadCrumbComponent {

  breadcrumb: MenuItem[] = [{
    label: 'Home'
  }];
  constructor(private breadcrumbService: BreadCrumbService) {
    this.breadcrumbService.breadcrumbChanged.subscribe(breadcrumb => {this.breadcrumb = breadcrumb});
  }
}
