import {Injectable} from '@angular/core';
import {MenuItem} from 'primeng/api';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class BreadCrumbService {

  default: MenuItem[] = [{
    label: 'Home'
  }];
  breadCrumbs = new BehaviorSubject<MenuItem[]>(this.default);
  public breadCrumbs$ = this.breadCrumbs.asObservable();

  constructor() {}

  setBreadcrumb(breadcrumb: MenuItem[]): void {
    this.breadCrumbs.next(breadcrumb);
  }

}
