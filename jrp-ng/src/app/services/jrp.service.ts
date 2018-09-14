import {SimplePerson} from '../dto/simple-person';
import {menuIems, judges, columnSettingsConfig} from './data';
import {Injectable} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { ComponentConfigValue } from '../dto/component-config-value';

@Injectable()
export class JrpService {

  constructor() {}

  getMenuItems(): MenuItem[] {
    return menuIems;
  }

  getCmecfMenuItems(): MenuItem[] {
    const cmecfMenuItems: MenuItem[] = [
      {label: 'Bankruptcy'},
      {label: 'Adversary'},
      {label: 'Query'},
      {label: 'Reports'},
      {label: 'Utilities'},
      {label: 'Help'},
      {label: 'What\'s New'},
      {label: 'Logout'}
    ];
    return cmecfMenuItems;
  }

  getJudges(): Observable<SimplePerson[]> {
    return Observable.of(judges);
  }

  getColumnSettings(): Observable<ComponentConfigValue[]>{
    return Observable.of(columnSettingsConfig);
  }
}
