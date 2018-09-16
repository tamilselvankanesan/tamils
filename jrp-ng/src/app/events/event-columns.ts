import { Column } from "../util/column";

export class EventColumns{
    columns: Column[] = [
        {
            field: 'caseNumber',
            header: 'Case'
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
            header: 'Doc'
        },
        {
            field: 'caseMatterDescription',
            header: 'Event'
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