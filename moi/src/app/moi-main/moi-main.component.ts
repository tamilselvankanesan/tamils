import { MoiData } from '../model/moidata';
import {MoiMainService} from '../service/moi-main.service';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-moi-main',
  templateUrl: './moi-main.component.html',
  styleUrls: ['./moi-main.component.css']
})
export class MoiMainComponent implements OnInit {

  moiData: MoiData[] = [];
  searchStr: string;
  addFlag = false;

  constructor(private moiService: MoiMainService) {
    this.moiData = this.moiService.getAllData();

  }

  ngOnInit() {
  }

  edit(data: MoiData) {
    data.editable = true;
  }

  add() {
    this.addFlag = true;
    let newMoi = new MoiData();
    newMoi.editable = true;
    //    this.moiData.push(newMoi);
    this.moiData.unshift(newMoi);
  }
  save(data: MoiData) {
    data.editable = false;
    if (!data.id) {
      this.addFlag = false;
    }
    console.log('save success');
  }
  delete(data: MoiData, index: number) {
    this.moiData.splice(index, 1);
    if (!data.id) {
      this.addFlag = false;
    }
  }
  cancel(data: MoiData, index: number) {
    if (!data.id) {
      this.delete(data, index);
    } else {
      // bring back the original
      this.moiService.getData(data.id).subscribe(oldData => {
        this.moiData[index] = oldData;
      });
      console.log('data ' + data.id);
    }
  }
  trackByIndex(index: number, obj: any): any {
    return index;
  }
}
