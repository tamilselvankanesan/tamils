import { Component, OnInit, Input } from '@angular/core';
import { Column } from '../util/column';
import { PacketFile } from '../dto/packet-file';
import { ColumnSettingsService } from '../services/column-settings.service';
import { OverlayPanel } from 'primeng/overlaypanel';

@Component({
  selector: 'jrp-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css']
})
export class FilesComponent implements OnInit {

  allColumns: Column[];
  selectedColumns: Column[];
  @Input('files') files: PacketFile[];

  constructor(private csService: ColumnSettingsService) { }

  ngOnInit() {
    this.allColumns = this.csService.getColumnSettings().getFileColumns().columns;
    this.csService.fileColumnsSelected$.subscribe(data => this.selectedColumns = data);

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
      console.log('all files columns 1'+JSON.stringify(this.selectedColumns));
    }
  }
  updateSelectedColumns(displayOptionsOverlay: OverlayPanel) {
    // clone the selection, otherwise check/uncheck directly affects the table
    this.csService.setFileColumnsSelected(JSON.parse(JSON.stringify(this.allColumns.filter(e => e.visible))));
    displayOptionsOverlay.hide();
  }

}
