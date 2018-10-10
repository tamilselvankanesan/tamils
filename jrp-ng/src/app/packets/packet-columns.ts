import { Column } from '../util/column';

export class PacketColumns {
    columns: Column[] = [
        {
            field: 'packetName',
            header: 'Packet Name',
            visible: true
        },
        {
            field: 'caseMatterDescription',
            header: 'Event',
            visible: true
        },
        {
            field: 'routedTo',
            header: 'To',
            visible: false
        },
        {
            field: 'caseChapter',
            header: 'Chapter',
            visible: false
        },
        {
            field: 'caseDocNum',
            header: 'Doc',
            visible: false
        },
        {
            field: 'caseType ',
            header: 'Case Type',
            visible: false
        },
        {
            field: 'eventType',
            header: 'Type',
            visible: false
        },
        {
            field: 'hearings',
            header: 'Hearing',
            visible: false
        },
        {
            field: 'dateCreated',
            header: 'Created',
            visible: false
        },
        {
            field: 'createdBy',
            header: 'Created By',
            visible: false
        },
        {
            field: 'lastUpdated',
            header: 'Last Updated',
            visible: false
        },
        {
            field: 'routedFrom',
            header: 'From',
            visible: false
        },
        {
            field: 'caseJudge',
            header: 'Judge',
            visible: false
        },
        {
            field: 'priority',
            header: 'Priority',
            visible: false
        },
        {
            field: 'status',
            header: 'Status',
            visible: false
        },
        {
            field: 'dueDate',
            header: 'Due',
            visible: false
        },
        {
            field: 'caseNumber',
            header: 'Case',
            visible: false
        }
    ];
    getColumns(): Column[] {
        return this.columns;
    }
}