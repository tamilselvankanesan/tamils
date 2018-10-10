import { Column } from '../util/column';

export class FileColumns {

    columns: Column[] = [
        {
            field: 'fileName',
            header: 'File',
            visible: true
        },
        {
            field: 'fileDescription',
            header: 'Description',
            visible: true
        },
        {
            field: 'fileCreatedDate',
            header: 'Added',
            visible: true
        },
        {
            field: 'fileCreator',
            header: 'Added by',
            visible: true
        },
    ];

    getColumns() {
        return this.columns;
    }
}