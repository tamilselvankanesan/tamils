import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  stateFlag: boolean;
  cityFlag: boolean;

  constructor() {}

  ngOnInit() {
  }
  importCountriesToNDB(): void {}

  addState(): void {
    this.stateFlag = true;
  }
  addCity(): void {
    this.cityFlag = true;
  }
}
