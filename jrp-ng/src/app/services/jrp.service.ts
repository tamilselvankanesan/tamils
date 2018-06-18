import { menuIems } from './data';
import {Injectable} from '@angular/core';
import {MenuItem} from 'primeng/api';

@Injectable()
export class JrpService {

  constructor() {}

  getMenuItems(): MenuItem[] {
    return menuIems;
  }

}
