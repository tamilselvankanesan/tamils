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
  }
  updateSelectedColumns(displayOptionsOverlay: OverlayPanel) {
    // clone the selection, otherwise check/uncheck directly affects the table
    this.csService.setEventColumnsSelected(JSON.parse(JSON.stringify(this.allColumns.filter(e => e.visible))));
    displayOptionsOverlay.hide();
  }

}
