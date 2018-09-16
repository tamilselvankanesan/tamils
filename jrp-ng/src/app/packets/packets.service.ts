import {Packet} from '../dto/packet';
import {Injectable, EventEmitter} from '@angular/core';
import { sampleData } from '../services/data';

@Injectable()
export class PacketsService {

  packets: Packet[] = [];
  packetsChanged = new EventEmitter<Packet[]>();
  constructor() {
  }
  getPackets(): Packet[] {
    return sampleData;
  }

  setPackets(packets: Packet[]) {
    this.packets = packets;
    this.packetsChanged.next(this.packets);
    console.log("packets changed");
  }
}
