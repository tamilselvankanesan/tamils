import { PacketEvent } from "./packet-event";
import { PacketNote } from "./packet-note";
import { PacketFile } from "./packet-file";

export class Packet {
  packetName?: string;
  packetId?: number;
  caseMatterDescription?: string;
  routedTo?: string;
  caseNumber?: string;
  showItems?: boolean;
  showFiles?: boolean;
  showNotes?: boolean;
  events?: PacketEvent[];
  notes?: PacketNote[];
  files?: PacketFile[];
}
