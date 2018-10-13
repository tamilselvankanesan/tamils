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
  expandAll = false;

  constructor(private packetService: PacketsService, private csService: ColumnSettingsService) {
    this.packetService.packets$.subscribe(packets => this.packets = packets);
    
  }

  ngOnInit() {
    this.allColumns = this.csService.getColumnSettings().getPacketColumns().columns;
    this.expandAll = false;
    this.csService.packetColumnsSelected.subscribe(data => this.selectedColumns = data);

    if(Array.isArray(this.selectedColumns) && this.selectedColumns.length >0){

      let allColumnsLocal = [];

      this.allColumns.forEach(e => {e.visible=false; allColumnsLocal.push(e)});
      this.allColumns = allColumnsLocal;

      this.selectedColumns.forEach(e => {
        let col = this.allColumns.find(a => e.header === a.header);
        let index = this.allColumns.indexOf(col);
        col.visible = true;
        this.allColumns[index] = col;
      });
    }
  }
  updateSelectedColumns(displayOptionsOverlay: OverlayPanel) {
    // clone the selection, otherwise check/uncheck directly affects the table
    this.csService.setPacketColumnsSelected(JSON.parse(JSON.stringify(this.allColumns.filter(e => e.visible))));
    displayOptionsOverlay.hide();
  }
  handleNavigation() {

  }
}
