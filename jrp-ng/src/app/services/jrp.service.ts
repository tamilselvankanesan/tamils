import { SimplePerson } from '../dto/simple-person';
import { menuIems, judges, columnSettingsConfig, judgesList } from './data';
import { Injectable } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { ComponentConfigValue } from '../dto/component-config-value';
import { Person } from '../dto/person';

@Injectable()
export class JrpService {

  private jrpMenus = new BehaviorSubject<MenuItem[]>(menuIems);
  public jrpMenus$ = this.jrpMenus.asObservable();

  constructor() {
    // update SaveSearch and Hearing menus here...
  }

  updateJrpMenus() {
    // this.jrpMenus.next(updatedMenu);
  }

  formatMenu() {

    menuIems.filter(e => e.label === '')[0];
    // menuIems.i

    menuIems.forEach(element => {
      if (element.label) {
      }
    });
  }

  getCmecfMenuItems(): MenuItem[] {
    const cmecfMenuItems: MenuItem[] = [
      { label: 'Bankruptcy' },
      { label: 'Adversary' },
      { label: 'Query' },
      { label: 'Reports' },
      { label: 'Utilities' },
      { label: 'Help' },
      { label: 'What\'s New' },
      { label: 'Logout' }
    ];
    return cmecfMenuItems;
  }

  getJudges(): Observable<SimplePerson[]> {
    return Observable.of(judges);
  }

  getColumnSettings(): Observable<ComponentConfigValue[]> {
    return Observable.of(columnSettingsConfig);
  }
  getJudgesList(): Observable<Person[]>{
    return Observable.of(judgesList);
  }
}
