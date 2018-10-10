import {Packet} from '../dto/packet';
import {Component, OnInit, Input} from '@angular/core';
import 'rxjs/add/operator/filter';
import { Column } from '../util/column';
import { ColumnSettingsService } from '../services/column-settings.service';

@Component({
  selector: 'app-packets',
  templateUrl: './packets.component.html',
  styleUrls: ['./packets.component.css']
})
export class PacketsComponent implements OnInit {

  @Input('packets') packets: Packet[] = [];
  selectedColumns: Column[];
  selectedPackets: Packet[] = [];
  constructor(private csSerive: ColumnSettingsService) {
  }

  ngOnInit() {
    this.csSerive.packetColumnsSelected$.subscribe(data => this.selectedColumns = data);
  }

  handleNavigation() {

  }
}
