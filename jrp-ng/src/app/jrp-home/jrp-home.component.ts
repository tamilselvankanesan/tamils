import { Packet } from '../dto/packet';
import { Component, OnInit } from '@angular/core';
import 'rxjs/add/operator/filter';
import { PacketsService } from '../packets/packets.service';
import { Column } from '../util/column';
import { ColumnSettingsService } from '../services/column-settings.service';
import { OverlayPanel } from 'primeng/overlaypanel';

@Component({
  selector: 'app-home',
  templateUrl: './jrp-home.component.html',
  styleUrls: ['./jrp-home.component.css']
})
export class JrpHomeComponent implements OnInit {

  packets: Packet[] = [];
  allColumns: Column[];
  selectedColumns: Column[];

  constructor(private packetService: PacketsService, private csService: ColumnSettingsService) {
    this.packetService.packets.subscribe(packets => this.packets = packets);
  }

  ngOnInit() {
    this.allColumns = this.csService.getColumnSettings().getPacketColumns().columns;
    this.csService.packetColumnsSelected.subscribe(data => this.selectedColumns = data);
  }
  updateSelectedColumns(displayOptionsOverlay: OverlayPanel) {
    // clone the selection, otherwise check/uncheck directly affects the table
    this.csService.setPacketColumnsSelected(JSON.parse(JSON.stringify(this.allColumns.filter(e => e.visible))));
    displayOptionsOverlay.hide();
  }
  handleNavigation() {

  }
}
