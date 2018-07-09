import {AdminService} from './admin.service';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  stateFlag: boolean;
  cityFlag: boolean;

  constructor(private adminService: AdminService) {}

  ngOnInit() {
  }
  importCountriesToNDB(): void {
    this.adminService.importCountriesToNDB();
  }

  addState(): void {
    this.stateFlag = true;
  }
  addCity(): void {
    this.cityFlag = true;
  }
}
