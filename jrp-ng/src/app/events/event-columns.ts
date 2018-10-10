import { Column } from '../util/column';

export class EventColumns{
    columns: Column[] = [
        {
            field: 'caseNumber',
            header: 'Case',
            visible: true
        },
        {
            field: 'caseChapter',
            header: 'Chapter'
        },
        {
            field: 'caseType',
            header: 'Type'
        },
        {
            field: 'caseDocNum',
            header: 'Doc',
            visible: true
        },
        {
            field: 'caseMatterDescription',
            header: 'Event',
            visible: true
        },
        {
            field: 'hearings',
            header: 'Hearing'
        },
        {
            field: 'caseMatterFiledDate',
            header: 'Date Filed'
        },
        {
            field: 'caseMatterFiledBy',
            header: 'Filed by'
        }
    ];
    getColumns():Column[]{
        return this.columns;
    }
}