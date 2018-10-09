import { Component, OnInit, Input } from '@angular/core';
import { Column } from '../util/column';
import { ComponentConfigValue } from '../dto/component-config-value';
import { ColumnSettings } from '../util/column-settings';
import { PacketNote } from '../dto/packet-note';
import { JRPConstants } from '../util/jrp-constants';

@Component({
  selector: 'jrp-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {

  allColumns: Column[];
  selectedColumns: Column[];
  @Input('savedColumns') savedColumns: ComponentConfigValue[] = [];
  cs = new ColumnSettings();
  @Input('notes')notes: PacketNote[]

  constructor() { }

  ngOnInit() {
    this.allColumns = this.cs.getNoteColumns().columns;
    this.selectedColumns = this.cs.getNoteColumns().columns.filter(c => this.savedColumns.some(cv => 
      cv.userInterfaceScreenFieldKey.startsWith(JRPConstants.NotesColumnPrefix.toString()) && cv.userInterfaceScreenFieldKey.endsWith(c.field)
    ));
  }

}
