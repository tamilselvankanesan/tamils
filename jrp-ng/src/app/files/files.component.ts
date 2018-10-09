import { Component, OnInit, Input } from '@angular/core';
import { Column } from '../util/column';
import { ComponentConfigValue } from '../dto/component-config-value';
import { ColumnSettings } from '../util/column-settings';
import { PacketFile } from '../dto/packet-file';
import { JRPConstants } from '../util/jrp-constants';

@Component({
  selector: 'jrp-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css']
})
export class FilesComponent implements OnInit {

  allColumns: Column[];
  selectedColumns: Column[];
  @Input('savedColumns') savedColumns: ComponentConfigValue[] = [];
  cs = new ColumnSettings();
  @Input('files')files: PacketFile[]
  constructor() { }

  ngOnInit() {
    this.allColumns = this.cs.getFileColumns().columns;
    this.selectedColumns = this.cs.getFileColumns().columns.filter(c => this.savedColumns.some(cv => 
      cv.userInterfaceScreenFieldKey.startsWith(JRPConstants.FilesColumnPrefix.toString()) && cv.userInterfaceScreenFieldKey.endsWith(c.field)
    ));
  }

}
