import { Component, OnInit, Input } from '@angular/core';
import { Column } from '../util/column';
import { ComponentConfigValue } from '../dto/component-config-value';
import { ColumnSettings } from '../util/column-settings';
import { JRPConstants } from '../util/jrp-constants';
import { PacketEvent } from '../dto/packet-event';

@Component({
  selector: 'jrp-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {

  allColumns: Column[];
  selectedColumns: Column[];
  @Input('savedColumns') savedColumns: ComponentConfigValue[] = [];
  cs = new ColumnSettings();
  @Input('events')events: PacketEvent[]

  constructor() {}

  ngOnInit() {
    this.allColumns = this.cs.getEventColumns().columns;
    this.selectedColumns = this.cs.getEventColumns().columns.filter(c => this.savedColumns.some(cv => 
      cv.userInterfaceScreenFieldKey.startsWith(JRPConstants.EventsColumnPrefix.toString()) && cv.userInterfaceScreenFieldKey.endsWith(c.field)
    ));
  }

}
