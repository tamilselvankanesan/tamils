import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
import { Rule } from '../shared/r-model';
import { EOShip } from '../shared/e-o-ship.model';
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
    this.setupOwnershipData();
  }

  setEOSelected(){
    console.log('toggle');
    if(this.showEO){
      this.rule.enhancedOwnershipSelected = 'Y';
    }else{
      this.rule.enhancedOwnershipSelected = 'N';
    }
  }

  setupOwnershipData(){
    let eo = new EOShip();
    let ownership = new OShip();
    ownership.businessUnit = [
      {
        id: 'BU1',text: 'Business Unit1'
      },
      {
        id: 'BU2',text: 'Business Unit2'
      }
    ];
    ownership.legalEntity = {legalEntity: 'Test Legal Entity', legalEntityID:'LE1'};
    ownership.primaryContact = {type: 'Investor', businessUnit: 'Some Business Unit'};
    ownership.additionalContacts = [
      {
        type: 'Some Type', businessUnit: 'ADD BU'
      }
    ];
    eo.ownerships = [];
    eo.ownerships.push(ownership);
    eo.name = 'New Enhanced ownership';
    this.rule.secondLineOwnership = eo;
  }

  deleteSecondLineRole(index: number){
    let ownerships = [...this.rule.secondLineOwnership.ownerships];
    ownerships.splice(index, 1);
    this.rule.secondLineOwnership.ownerships = ownerships;
  }
  addSecondLineRole(){
    let ownership = new OShip();
    let ownerships = [...this.rule.secondLineOwnership.ownerships];
    let count = this.rule.secondLineOwnership.ownerships.length + 1;
    ownership.businessUnit = [
      {
        id: 'BU '+count,text: 'Business Unit'
      },
      {
        id: 'BU '+ count,text: 'Business Unit2'
      }
    ];
    ownership.legalEntity = {legalEntity: 'Test Legal Entity', legalEntityID:'LE '+ count};
    ownership.primaryContact = {type: 'Investor', businessUnit: 'Some Business Unit'};
    ownership.additionalContacts = [
      {
        type: 'Some Type', businessUnit: 'ADD BU'
      }
    ];
    ownerships.push(ownership);
    this.rule.secondLineOwnership.ownerships = ownerships;
  }
  save(){
    console.log('result is :'+JSON.stringify(this.rule));
  }
}
