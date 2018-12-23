import { Packet } from '../dto/packet';
import { Component, OnInit, Input } from '@angular/core';
import 'rxjs/add/operator/filter';
import { Column } from '../util/column';
import { ColumnSettingsService } from '../services/column-settings.service';
import { PacketsService } from './packets.service';

@Component({
  selector: 'app-packets',
  templateUrl: './packets.component.html',
  styleUrls: ['./packets.component.css']
})
export class PacketsComponent implements OnInit {

  @Input('packets') packets: Packet[] = [];
  @Input() expandAll: boolean = false;
  selectedColumns: Column[];
  selectedPackets: Packet[] = [];
  expandedRows: {} = {};
  moreActions = false;

  constructor(private csSerive: ColumnSettingsService, private packetsService: PacketsService) {
  }

  ngOnInit() {
    this.csSerive.packetColumnsSelected$.subscribe(data => this.selectedColumns = data);
    this.packetsService.moreActions$.subscribe(data => this.moreActions = data);
    if (this.expandAll) {
      this.packets.forEach(e => { this.expandedRows[e.packetId] = 1 });
    }
  }

  editPacket(index: number) {

  }
  handleNavigation() {

  }
}
