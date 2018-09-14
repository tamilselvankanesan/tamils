import {Packet} from '../dto/packet';
import {Component, OnInit, Input} from '@angular/core';
import 'rxjs/add/operator/filter';
import { ColumnSettings } from '../util/column-settings';
import { ComponentConfigValue } from '../dto/component-config-value';
import { Column } from '../util/column';

@Component({
  selector: 'app-packets',
  templateUrl: './packets.component.html',
  styleUrls: ['./packets.component.css']
})
export class PacketsComponent implements OnInit {

  @Input('packets') packets: Packet[] = [];
  @Input('savedColumns') savedColumns: ComponentConfigValue[] = [];
  allColumns: Column[];
  selectedColumns: Column[];
  cs = new ColumnSettings();
  selectedPackets: Packet[] = [];
  
  constructor() {
    this.ngOnInit();
  }

  ngOnInit() {
    this.allColumns = this.cs.getPacketColumns().columns;
    // this.selectedColumns = this.cs.getPacketColumns().columns.filter(c => this.savedColumns.some(cv => 
    //   cv.userInterfaceScreenFieldKey.startsWith("panelPacketListColumn") && cv.userInterfaceScreenFieldKey.endsWith(c.field)
    // ));
    console.log('init packets ');
  }

  handleNavigation() {

  }


  
}
