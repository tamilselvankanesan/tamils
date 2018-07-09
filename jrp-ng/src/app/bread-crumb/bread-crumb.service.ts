import {Injectable, EventEmitter} from '@angular/core';
import {MenuItem} from 'primeng/api';

@Injectable()
export class BreadCrumbService {
  items: MenuItem[] = [];
  public breadcrumbChanged: EventEmitter<MenuItem[]> = new EventEmitter<MenuItem[]>();
  constructor() {}

  getBreadcrumb(): MenuItem[] {
    return this.items;
  }

  setBreadcrumb(breadcrumb: MenuItem[]): void {
    this.items = breadcrumb;
    this.breadcrumbChanged.next(this.items);
  }

}
