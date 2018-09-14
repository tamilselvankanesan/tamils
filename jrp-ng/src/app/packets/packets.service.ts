import {Packet} from '../dto/packet';
import {Injectable, EventEmitter} from '@angular/core';

const sampleData: Packet[] = [
  {packetId: 1, packetName: '04-44444 # 1 Test packet One Twenty', caseMatterDescription: new Date().toLocaleString(), caseNumber: '04-444441 raq11ddd'},
  {packetId: 2, packetName: '04-44444 # 1 Test packet Two', caseMatterDescription: new Date().toLocaleString(), caseNumber: '04-444441 raq111'},
  {packetId: 3, packetName: '04-44444 # 1 Test packet Three', caseMatterDescription: new Date().toLocaleString(), caseNumber: '04-444441 raq'}
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
