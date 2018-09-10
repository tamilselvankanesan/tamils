import {Packet} from '../dto/packet';
import {Injectable, EventEmitter} from '@angular/core';

const sampleData: Packet[] = [
  {packetId: 1, packetName: '04-44444 # 1 Test packet One Twenty', docketText: new Date().toLocaleString()},
  {packetId: 2, packetName: '04-44444 # 1 Test packet Two', docketText: new Date().toLocaleString()},
  {packetId: 3, packetName: '04-44444 # 1 Test packet Three', docketText: new Date().toLocaleString()}
];

@Injectable()
export class PacketsService {

  packets: Packet[] = [];
  packetsChanged = new EventEmitter<Packet[]>();
  constructor() {
    this.packets = sampleData;
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
