import { Component, OnInit, Input } from '@angular/core';
import { Column } from '../util/column';
import { PacketEvent } from '../dto/packet-event';
import { ColumnSettingsService } from '../services/column-settings.service';
import { OverlayPanel } from 'primeng/overlaypanel';

@Component({
  selector: 'jrp-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {

  allColumns: Column[];
  selectedColumns: Column[];
  @Input('events') events: PacketEvent[];

  constructor(private csService: ColumnSettingsService) { }

  ngOnInit() {
    this.allColumns = this.csService.getColumnSettings().getEventColumns().columns;
    this.csService.eventColumnsSelected$.subscribe(data => this.selectedColumns = data);

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
    this.csService.setEventColumnsSelected(JSON.parse(JSON.stringify(this.allColumns.filter(e => e.visible))));
    displayOptionsOverlay.hide();
  }

}
