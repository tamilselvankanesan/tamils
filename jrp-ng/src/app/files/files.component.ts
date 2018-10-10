import { Component, OnInit, Input } from '@angular/core';
import { Column } from '../util/column';
import { PacketFile } from '../dto/packet-file';
import { ColumnSettingsService } from '../services/column-settings.service';

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
  }

}
