import { Component, OnInit, Input } from '@angular/core';
import { Column } from '../util/column';
import { PacketNote } from '../dto/packet-note';
import { ColumnSettingsService } from '../services/column-settings.service';

@Component({
  selector: 'jrp-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {

  allColumns: Column[];
  selectedColumns: Column[];
  @Input('notes') notes: PacketNote[];

  constructor(private csService: ColumnSettingsService) { }

  ngOnInit() {
    this.allColumns = this.csService.getColumnSettings().getNoteColumns().columns;
    this.csService.noteColumnsSelected$.subscribe(data => this.selectedColumns = data);
  }

}
