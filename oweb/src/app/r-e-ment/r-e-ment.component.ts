import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
import { Rule } from '../shared/r-model';
import { OShip } from '../shared/o-ship.model';

@Component({
  selector: 'app-r-e-ment',
  templateUrl: './r-e-ment.component.html',
  styleUrls: ['./r-e-ment.component.css']
})
export class REMentComponent implements OnInit {

  @Input()rule: Rule;
  showEO = false;
  showSLOTable = false;
  
  constructor() {
   }

  ngOnInit() {
    
  }

  setEOSelected(){
    console.log('toggle');
    this.showEO = !this.showEO;
    if(this.showEO){
      this.rule.enhancedOwnershipSelected = 'Y';
      this.rule.setupOwnershipData();
    }else{
      this.rule.enhancedOwnershipSelected = 'N';
    }
  }
  
  deleteSecondLineRole(index: number){
    let ownerships = [...this.rule.secondLineOwnership.ownerships];
    ownerships.splice(index, 1);
    this.rule.secondLineOwnership.ownerships = ownerships;
  }
  addSecondLineRole(){
    let ownerships = [...this.rule.secondLineOwnership.ownerships];
    ownerships.push(new OShip());
    this.rule.secondLineOwnership.ownerships = ownerships;
  }
  save(){
    console.log('result is :'+JSON.stringify(this.rule));
  }
}
