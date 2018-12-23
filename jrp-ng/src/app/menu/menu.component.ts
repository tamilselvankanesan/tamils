import {JrpService} from '../services/jrp.service';
import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  items: MenuItem[];
  cmecfMenuItems: MenuItem[];
  constructor(private jrpService: JrpService) {
  }

  ngOnInit() {
    console.log('init menu');
    this.jrpService.jrpMenus$.subscribe( menus => {
      console.log('size '+menus.length);
      this.items = menus
    });
    console.log('menu size ->'+this.items.length);
    this.cmecfMenuItems = this.jrpService.getCmecfMenuItems();
  }

}
