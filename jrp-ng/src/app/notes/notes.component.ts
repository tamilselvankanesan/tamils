import { Component, OnInit, Input } from '@angular/core';
import { Column } from '../util/column';
import { PacketNote } from '../dto/packet-note';
import { ColumnSettingsService } from '../services/column-settings.service';
import { OverlayPanel } from 'primeng/overlaypanel';

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
    this.csService.setNoteColumnsSelected(JSON.parse(JSON.stringify(this.allColumns.filter(e => e.visible))));
    displayOptionsOverlay.hide();
  }

}
