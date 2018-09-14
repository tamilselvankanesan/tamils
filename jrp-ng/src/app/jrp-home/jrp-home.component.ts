import {BreadCrumbService} from '../bread-crumb/bread-crumb.service';
import {Packet} from '../dto/packet';
import {Time} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, PRIMARY_OUTLET, NavigationEnd} from '@angular/router';
import {MenuItem} from 'primeng/api';
import 'rxjs/add/operator/filter';
import { PacketsService } from '../packets/packets.service';
import { PacketColumns } from '../util/packet-columns';
import { ColumnSettings } from '../util/column-settings';
import { ComponentConfigValue } from '../dto/component-config-value';
import { JrpService } from '../services/jrp.service';
import { Column } from '../util/column';

@Component({
  selector: 'app-home',
  templateUrl: './jrp-home.component.html',
  styleUrls: ['./jrp-home.component.css']
})
export class JrpHomeComponent implements OnInit {

  text = 'hello';
  packets: Packet[] = [];
  componentConfigValue: ComponentConfigValue[] = [];
  packetColumns: Column[];
  selectedPacketColumns: Column[];
  cs = new ColumnSettings();
  selectedPackets: Packet[] = [];

  constructor(private activatedRoute: ActivatedRoute, private breadcrumbService: BreadCrumbService, private router: Router,
    private packetService: PacketsService, private jrpService: JrpService) {
    this.text = new Date().toLocaleString();
    this.jrpService.getColumnSettings().subscribe(data => this.componentConfigValue = data);
    this.packets = this.packetService.packets;

    this.ngOnInit();
  }

  ngOnInit() {
    this.packetColumns = this.cs.getPacketColumns().columns;
    this.selectedPacketColumns = this.cs.getPacketColumns().columns.filter(c => this.componentConfigValue.some(cv => 
      cv.userInterfaceScreenFieldKey.startsWith("panelPacketListColumn") && cv.userInterfaceScreenFieldKey.endsWith(c.field)
    ));
  }

  handleNavigation() {

  }


  
}
