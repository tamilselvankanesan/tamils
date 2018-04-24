import {MenuItem} from './data/menu-item';
import {JrpService} from './services/jrp.service';
import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  items: MenuItem[];
  constructor(private jrpService: JrpService) {
    this.items = this.jrpService.getMenuItems();
  }
}
