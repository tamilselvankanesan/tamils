import {Packet} from '../dto/packet';
import {Injectable} from '@angular/core';
import { sampleData } from '../services/data';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class PacketsService {

  packets = new BehaviorSubject<Packet[]>([]);
  public packets$ = this.packets.asObservable();

  private moreActions = new BehaviorSubject<boolean>(false);
  public moreActions$ = this.moreActions.asObservable();

  constructor() {
  }
  getPackets(): Packet[] {
    return sampleData;
  }

  setPackets(packets: Packet[]) {
    this.packets.next(packets);
    console.log("packets changed");
  }

  setMoreActions(moreActions: boolean){
    this.moreActions.next(moreActions);
  }
}
