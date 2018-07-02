import {JrpService} from '../services/jrp.service';
import {Component, OnInit, Input, ViewChild} from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  items: MenuItem[];
  constructor(private jrpService: JrpService) {}

  ngOnInit() {
    this.items = this.jrpService.getMenuItems();
  }

}
