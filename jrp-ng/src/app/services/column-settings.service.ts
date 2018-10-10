import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { ColumnSettings } from '../util/column-settings';
import { ComponentConfigValue } from '../dto/component-config-value';
import { columnSettingsConfig } from './data';
import { Column } from '../util/column';
import { JRPConstants } from '../util/jrp-constants';

@Injectable()
export class ColumnSettingsService {

  cs = new ColumnSettings();
  componentConfigValue: ComponentConfigValue[] = [];

  packetColumnsSelected = new BehaviorSubject<Column[]>([]);
  public packetColumnsSelected$ = this.packetColumnsSelected.asObservable();

  eventColumnsSelected = new BehaviorSubject<Column[]>([]);
  public eventColumnsSelected$ = this.eventColumnsSelected.asObservable();

  noteColumnsSelected = new BehaviorSubject<Column[]>([]);
  public noteColumnsSelected$ = this.noteColumnsSelected.asObservable();

  fileColumnsSelected = new BehaviorSubject<Column[]>([]);
  public fileColumnsSelected$ = this.fileColumnsSelected.asObservable();

  constructor() {
    // get the saved columns and set componentConfigValue TODO...
    this.componentConfigValue = columnSettingsConfig;
    this.initPacketColumnsSelected();
  }

  initPacketColumnsSelected() {
    const cs = new ColumnSettings();
    this.setPacketColumnsSelected(this.filterSelectedColumns(JRPConstants.PacketsColumnPrefix.toString(), cs.getPacketColumns().columns));
    this.setEventColumnsSelected(this.filterSelectedColumns(JRPConstants.EventsColumnPrefix.toString(), cs.getEventColumns().columns));
    this.setNoteColumnsSelected(this.filterSelectedColumns(JRPConstants.NotesColumnPrefix.toString(), cs.getNoteColumns().columns));
    this.setfileColumnsSelected(this.filterSelectedColumns(JRPConstants.FilesColumnPrefix.toString(), cs.getFileColumns().columns));
  }
  filterSelectedColumns(type: string, columns: Column[]) {
    return columns.filter(c => this.componentConfigValue.some(cv =>
      cv.userInterfaceScreenFieldKey.startsWith(type) && cv.userInterfaceScreenFieldKey.endsWith(c.field)
    ));
  }
  setPacketColumnsSelected(columns: Column[]) {
    this.packetColumnsSelected.next(columns);
  }
  setEventColumnsSelected(columns: Column[]) {
    this.eventColumnsSelected.next(columns);
  }
  setNoteColumnsSelected(columns: Column[]) {
    this.noteColumnsSelected.next(columns);
  }
  setfileColumnsSelected(columns: Column[]) {
    this.fileColumnsSelected.next(columns);
  }
  getColumnSettings() {
    return this.cs;
  }
}
