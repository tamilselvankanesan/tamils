import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { TabNames } from 'src/app/utils/tab-names';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})

export class AccountComponent implements OnInit {

  tabNames= TabNames;
  selectedTabName = TabNames.PROFESSIONAL_INFO;
  constructor() {
    console.log(this.selectedTabName);
   }

  ngOnInit() {
  }

}
