import { MoiGroup } from '../model/moi-group';
import {MoiMainService} from '../service/moi-main.service';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-moi-home',
  templateUrl: './moi-home.component.html',
  styleUrls: ['./moi-home.component.css']
})
export class MoiHomeComponent implements OnInit {

  moiGroups: MoiGroup[] = [];
  constructor(private moiService: MoiMainService) {

    this.moiService.getGroups(10).subscribe(data => {this.moiGroups = data;});

  }

  ngOnInit() {
  }

}
