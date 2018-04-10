import {MenuItem} from '../data/menu-item';
import {JrpService} from '../services/jrp.service';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  navItems: MenuItem[];

  constructor(private jrpService: JrpService) {}

  ngOnInit() {
    this.navItems = this.jrpService.getMenuItems();
  }

}
