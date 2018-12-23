import { Component, OnInit } from '@angular/core';
import { PacketsService } from '../packets/packets.service';

@Component({
  selector: 'jrp-more-actions',
  templateUrl: './more-actions.component.html',
  styleUrls: ['./more-actions.component.css']
})
export class MoreActionsComponent implements OnInit {

  moreActions = false;

  constructor(private packetsService: PacketsService) {
    this.packetsService.moreActions$.subscribe(data => this.moreActions = data);
   }

  ngOnInit() {
  }

  toggleMoreActions(){
    this.moreActions = !this.moreActions;
    this.packetsService.setMoreActions(this.moreActions);
  }
}
