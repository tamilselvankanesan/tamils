import {MenuItem} from '../data/menu-item';
import { Data, menuIems } from './data';
import {Injectable} from '@angular/core';

@Injectable()
export class JrpService {

  constructor() {}

  getMenuItems(): MenuItem[] {
    return menuIems;
  }

}
