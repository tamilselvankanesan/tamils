import {Packet} from '../dto/packet';
import {Component, OnInit} from '@angular/core';
import 'rxjs/add/operator/filter';
import { PacketsService } from '../packets/packets.service';
import { ColumnSettings } from '../util/column-settings';
import { ComponentConfigValue } from '../dto/component-config-value';
import { JrpService } from '../services/jrp.service';

@Component({
  selector: 'app-home',
  templateUrl: './jrp-home.component.html',
  styleUrls: ['./jrp-home.component.css']
})
export class JrpHomeComponent implements OnInit {

  packets: Packet[] = [];
  componentConfigValue: ComponentConfigValue[] = [];
  cs = new ColumnSettings();
  
  constructor(private packetService: PacketsService, private jrpService: JrpService) {
    this.jrpService.getColumnSettings().subscribe(data => this.componentConfigValue = data);
    this.packets = this.packetService.packets;

  }

  ngOnInit() { 
  }

  handleNavigation() {

  }


  
}
