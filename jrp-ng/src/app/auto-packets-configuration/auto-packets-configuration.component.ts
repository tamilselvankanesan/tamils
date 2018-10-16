import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/api';
import { JrpService } from '../services/jrp.service';

@Component({
  selector: 'jrp-auto-packets-configuration',
  templateUrl: './auto-packets-configuration.component.html',
  styleUrls: ['./auto-packets-configuration.component.css']
})
export class AutoPacketsConfigurationComponent implements OnInit {

  selectedJudgeId: number;
  judges: SelectItem[];
  constructor(private jrpService: JrpService) { }

  ngOnInit() {
    this.jrpService.getJudgesList().subscribe(data => {
      this.judges = data.map(d => ({label: d.fullName, value: d.prid}));
    });
  }

}
