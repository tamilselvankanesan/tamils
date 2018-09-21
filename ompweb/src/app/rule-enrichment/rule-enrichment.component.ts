import { Component, OnInit } from '@angular/core';
import { Rule } from '../shared/rule-model';
import { EnhancedOwnership } from '../shared/enhanced-ownership.model';
import { Ownership } from '../shared/ownership.model';

@Component({
  selector: 'app-rule-enrichment',
  templateUrl: './rule-enrichment.component.html',
  styleUrls: ['./rule-enrichment.component.css']
})
export class RuleEnrichmentComponent implements OnInit {

  rule: Rule;
  showEnhancedOwnership = false;
  showSecondLineOwnershipTable = false;

  constructor() {
    this.rule = new Rule();
    this.setupOwnershipData();
   }

  ngOnInit() {
  }

  setEnhancedOwnershipSelected(){
    console.log('toggle');
    if(this.showEnhancedOwnership){
      this.rule.enhancedOwnershipSelected = 'Y';
    }else{
      this.rule.enhancedOwnershipSelected = 'N';
    }
  }

  setupOwnershipData(){
    let eo = new EnhancedOwnership();
    let ownership = new Ownership();
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
    this.rule.secondLineOwnership.ownerships.splice(index, 1);
  }
  addSecondLineRole(){
    let ownership = new Ownership();
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
    this.rule.secondLineOwnership.ownerships.push(ownership);
  }
  save(){
    console.log('result is :'+JSON.stringify(this.rule));
  }
}
