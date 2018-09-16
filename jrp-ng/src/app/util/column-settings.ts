import { PacketColumns } from "../packets/packet-columns";
import { EventColumns } from "../events/event-columns";

export class ColumnSettings {

    packetColumns = new PacketColumns();
    eventColumns = new EventColumns();
    constructor(){
    }

    updatePacketColumns(){}

    updateNoteColumns(){}

    updateFileColumns(){}

    getPacketColumns(){
        return this.packetColumns;
    }
    getEventColumns(){
        return this.eventColumns;
    }
    getNoteColumns(){}

    getFileColumns(){}

}
