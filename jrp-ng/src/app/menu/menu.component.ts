import {JrpService} from '../services/jrp.service';
import {Component, OnInit, Input, ViewChild, ViewEncapsulation} from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MenuComponent implements OnInit {

  items: MenuItem[];
  cmecfMenuItems: MenuItem[];
  constructor(private jrpService: JrpService) {}

  ngOnInit() {
    this.items = this.jrpService.getMenuItems();
    this.cmecfMenuItems = this.jrpService.getCmecfMenuItems();
  }

}
