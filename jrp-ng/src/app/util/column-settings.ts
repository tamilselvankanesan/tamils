import { PacketColumns } from "./packet-columns";

export class ColumnSettings {

    packetColumns = new PacketColumns();

    constructor(){
    }

    updatePacketColumns(){}

    updateNoteColumns(){}

    updateFileColumns(){}

    getPacketColumns(){
        return this.packetColumns;
    }
    getNoteColumns(){}

    getFileColumns(){}
}
