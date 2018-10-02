import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
import { Rule } from '../shared/r-model';
import { OShip } from '../shared/o-ship.model';
import { REService } from './r-e.service';
import { EOShip } from '../shared/e-o-ship.model';

@Component({
  selector: 'app-r-e-ment',
  templateUrl: './r-e-ment.component.html',
  styleUrls: ['./r-e-ment.component.css']
})
export class REMentComponent implements OnInit {

  @Input()rule: Rule;
  showEO = false;
  showSLOTable = false;
  secondBus: any[];
  showDialog = false;
  selectedBus: any[] = [];
  
  constructor(private reService: REService) {
    
   }

  ngOnInit() {
    this.reService.getSecondBUs("").subscribe(
      response => {
        let secondBusTemp = response.map(
           i => ({...i, breadCrumb: null}));

        this.secondBus = secondBusTemp.map(item => ({
          label: item.text, value: item
        }));

        this.selectedBus = [...this.secondBus];
        //this.selectedBus.splice(0, this.selectedBus.length);
      });
  }


  setupOwnershipData(){
    if(this.rule.ownershipdata.secondLineOwnership){
      if(Array.isArray(this.rule.ownershipdata.secondLineOwnership.ownerships.ownership)){

      }
    }
    const secondLineOwnership = new EOShip();
    const firstLineOwnership = new EOShip();

    this.rule.ownershipdata.secondLineOwnership = secondLineOwnership;
    this.rule.ownershipdata.firstLineOwnership = firstLineOwnership;

    this.rule.ownershipdata.secondLineOwnership.ownerships = {ownership : [new OShip()]};
    this.rule.ownershipdata.firstLineOwnership.ownerships = {ownership : [new OShip()]};

  }

  setEOSelected(){
    console.log('toggle');
    this.showEO = !this.showEO;
    if(this.showEO){
      this.rule.ownershipdata.enhancedOwnershipSelected = 'Y';
      this.setupOwnershipData();
    }else{
      this.rule.ownershipdata.enhancedOwnershipSelected = 'N';
    }
  }
  
  deleteSecondLineRole(index: number){
    let ownerships = [...this.rule.ownershipdata.secondLineOwnership.ownerships.ownership];
    ownerships.splice(index, 1);
    this.rule.ownershipdata.secondLineOwnership.ownerships.ownership = ownerships;
  }
  addSecondLineRole(){
    let ownerships = [...this.rule.ownershipdata.secondLineOwnership.ownerships.ownership];
    ownerships.push(new OShip());
    this.rule.ownershipdata.secondLineOwnership.ownerships.ownership = ownerships;
  }
  save(){
    console.log('result is :'+JSON.stringify(this.rule));
    console.log('result is 1 1 1 :'+JSON.stringify(this.selectedBus));
  }

  openDialog() {
    this.showDialog = true;
}
}
