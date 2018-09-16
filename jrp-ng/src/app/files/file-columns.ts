import { Column } from "../util/column";

export class FileColumns{

    columns: Column[] = [
        {
            field: 'fileName',
            header: 'File'
        },
        {
            field: 'fileDescription',
            header: 'Description'
        },
        {
            field: 'fileCreatedDate',
            header: 'Added'
        },
        {
            field: 'fileCreator',
            header: 'Added by'
        },
    ];

    getColumns(){
        return this.columns;
    }
}