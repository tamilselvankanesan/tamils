import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/api';
import { JrpService } from '../services/jrp.service';
import { apcModels } from '../services/data';

@Component({
  selector: 'jrp-auto-packets-configuration',
  templateUrl: './auto-packets-configuration.component.html',
  styleUrls: ['./auto-packets-configuration.component.css']
})
export class AutoPacketsConfigurationComponent implements OnInit {

  selectedJudgeId: number;
  judges: SelectItem[];
  rules = apcModels;
  selectedRules: any = [];
  add = false;
  constructor(private jrpService: JrpService) { }

  ngOnInit() {
    this.jrpService.getJudgesList().subscribe(data => {
      this.judges = data.map(d => ({label: d.fullName, value: d.prid}));
    });
  }
  onJudgeSelect(){
    console.log('selected judge is '+this.selectedJudgeId);
    this.add = false;
  }

}
