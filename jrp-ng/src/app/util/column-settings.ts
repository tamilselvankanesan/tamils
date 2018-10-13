import { PacketColumns } from '../packets/packet-columns';
import { EventColumns } from '../events/event-columns';
import { NoteColumns } from '../notes/note-columns';
import { FileColumns } from '../files/file-columns';

export class ColumnSettings {

    packetColumns = new PacketColumns();
    eventColumns = new EventColumns();
    noteColumns = new NoteColumns();
    fileColumns = new FileColumns();
    constructor() {
    }

    updatePacketColumns() { }

    updateNoteColumns() { }

    updateFileColumns() { }

    getPacketColumns() {
        return this.packetColumns;
    }
    getEventColumns() {
        return this.eventColumns;
    }
    getNoteColumns() {
        return this.noteColumns;
    }
    getFileColumns() {
        return this.fileColumns;
    }
    
}
