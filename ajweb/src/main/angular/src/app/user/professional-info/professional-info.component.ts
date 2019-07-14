import { Component, OnInit } from '@angular/core';
import { TabNames } from 'src/app/utils/tab-names';

@Component({
  selector: 'app-professional-info',
  templateUrl: './professional-info.component.html',
  styleUrls: ['./professional-info.component.css']
})
export class ProfessionalInfoComponent implements OnInit {

  selectedTabName: string;
  tabNames = TabNames;

  constructor() {
    this.selectedTabName = TabNames.PERSONAL_INFO;
   }

  ngOnInit() {
  }

}
