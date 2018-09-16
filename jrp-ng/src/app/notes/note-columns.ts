import { Column } from "../util/column";

export class NoteColumns{
    columns: Column[] = [
    {
        field: 'subject', 
        header: 'Subject'
    },
    {
        field: 'notesEntry', 
        header: 'Note'
    },
    {
        field: 'userType', 
        header: 'User Type'
    },
    {
        field: 'userName', 
        header: 'Author'
    },
    {
        field: 'to', 
        header: 'To'
    },
    {
        field: 'dateEntered', 
        header: 'Entered'
    }

    ];
    getColumns(){
        return this.columns;
    }
}