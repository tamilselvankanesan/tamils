import {SimplePerson} from '../dto/simple-person';
import {PacketsComponent} from '../packets/packets.component';
import {MenuItem} from 'primeng/api';
import {RouterModule} from '@angular/router';
import { ComponentConfigValue } from '../dto/component-config-value';
import { Packet } from '../dto/packet';

export const menuIems: MenuItem[] = [
  {
    label: 'Judge Review Packets'
  },
  {
    label: 'Inbox',
    items: [{
      label: 'All',
      routerLink: 'inbox/11'
    },
    {
      label: 'New',
      items: [{
        label: 'All New',
        routerLink: 'inbox/111'
      },
      {
        label: 'Packet',
        routerLink: 'inbox/112'
      },
      {
        label: 'Other - Claims (BK only)',
        routerLink: 'inbox/113'
      },
      {
        label: 'Other - Miscellaneous',
        routerLink: 'inbox/114'
      },
      ]
    },
    {
      label: 'Overdue',
      items: [{
        label: 'All',
        routerLink: 'inbox/121',
        items: [{label: 'Test', routerLink: 'inbox/1211'}]
      },
      {
        label: 'One day overdue',
        routerLink: 'inbox/122'
      },
      {
        label: 'One week overdue',
        routerLink: 'inbox/123'
      }
      ]
    },
    {
      label: 'Status',
      items: [{
        label: 'Overdue',
        routerLink: 'inbox/131'
      },
      {
        label: 'New',
        routerLink: 'inbox/132'
      },
      {
        label: 'Closed',
        routerLink: 'inbox/133'
      },
      {
        label: 'Unassigned',
        routerLink: 'inbox/134'
      }
      ]
    },
    {
      label: 'Updated',
      items: [{
        label: 'Today',
        routerLink: 'inbox/141'
      },
      {
        label: 'This Week',
        routerLink: 'inbox/142'
      },
      {
        label: 'This Month',
        routerLink: 'inbox/143'
      },
      {
        label: 'Date Range',
        routerLink: 'inbox/144'
      }
      ]
    }
    ]
  },
  {
    label: 'Packet Searching',
    items: [
      {
        label: 'Search',
        items: [
          {label: 'Advanced', routerLink: 'search/211'},
          {label: 'Case/Party', routerLink: 'search/212'},
          {label: 'Date', routerLink: 'search/213'},
          {label: 'Packet', routerLink: 'search/214'}
        ]
      },
      {
        label: 'Hearing', routerLink: 'search/22',
        items: [{
          label: 'Judge Harmon',
          items: [{
            label: 'Today', routerLink: 'search/221'
          },
          {label: 'This Week', routerLink: 'search/222'}]
        }]
      },
      {label: 'Saved Search', routerLink: 'search/23'}
    ]
  },
  {
    label: 'Create',
    items: [
      {label: 'New Packet', routerLink: 'create/31'},
      {label: 'Other - Claims (BK only)', routerLink: 'create/32'},
      {label: 'Other - Miscellaneous', routerLink: 'create/33'}
    ]
  },
  {
    label: 'Configuration',
    items: [
      {label: 'My Configuration', routerLink: 'configuration/41'},
      {label: 'NextGen Configuration', routerLink: 'configuration/42'}
    ]
  },
  {
    label: 'About',
    items: [
      {label: 'About', routerLink: 'inbox/51'},
      {label: 'Tutorial', routerLink: 'inbox/52'}
    ]
  }
];


export const judges: SimplePerson[] = [
  {id: 1, name: 'Aguila, Ricky Middle, II'},
  {id: 2, name: 'AJTA, JudgeTwoB'}
];

export const columnSettingsConfig: ComponentConfigValue[] = [
  {userInterfaceScreenFieldKey: 'panelPacketListColumn_packetName',genericStringValue: 'ASC'},
  {userInterfaceScreenFieldKey: 'panelPacketListColumn_caseMatterDescription',genericStringValue: 'ASC'},
  {userInterfaceScreenFieldKey: 'panelPacketListColumn_caseNumber',genericStringValue: 'ASC'},
  
  {userInterfaceScreenFieldKey: 'panelPacketListItemsColumn_caseDocNum',genericStringValue: 'ASC'},
  {userInterfaceScreenFieldKey: 'panelPacketListItemsColumn_caseMatterDescription',genericStringValue: 'ASC'},
  {userInterfaceScreenFieldKey: 'panelPacketListItemsColumn_caseNumber',genericStringValue: 'ASC'},

  {userInterfaceScreenFieldKey: 'panelPacketListNotesColumn_subject',genericStringValue: 'ASC'},
  {userInterfaceScreenFieldKey: 'panelPacketListNotesColumn_notesEntry',genericStringValue: 'ASC'},
  {userInterfaceScreenFieldKey: 'panelPacketListNotesColumn_dateEntered',genericStringValue: 'ASC'}
];

export const sampleData: Packet[] = [
  {packetId: 1, packetName: '04-44444 # 1 Test packet One Twenty', caseMatterDescription: new Date().toLocaleString(), caseNumber: '04-444441 raq11ddd',
        events: [
          {caseNumber: '04-44444 Test Case NUmber 1',
          caseMatterDescription: 'Motion to Compel',
          caseMatterFiledBy: 'Tamilselvan Balasubramaniam',
          caseMatterFiledDate: '01/01/2018',
          caseDocNum: 1
          },
          {caseNumber: '04-44446 Test Case NUmber 1',
          caseMatterDescription: 'Motion to Reconsider',
          caseMatterFiledBy: 'Tamilselvan Balasubramaniam',
          caseMatterFiledDate: '02/01/2018',
          caseDocNum: 120
          }
        ],
        notes: [
          {
            subject: 'Test Note',
            notesEntry: 'Hello world. This is a test note mode.',
            dateEntered: '01/01/2017'
          },
          {
            subject: 'Test Note 222 ',
            notesEntry: 'Hello world. This is a test note note ABC 1.',
            dateEntered: '01/01/2016'
          }
        ],
        files: [
          {
            fileName: 'Test.pdf',
            fileCreatedDate: '01/01/2016',
            fileDescription: 'Sample Tets PDF file'
          },
          {
            fileName: 'Test2.pdf',
            fileCreatedDate: '01/01/2018',
            fileDescription: 'Sample Tets PDF file 2'
          }
        ]
      },
  {packetId: 2, packetName: '04-44444 # 1 Test packet Two', caseMatterDescription: new Date().toLocaleString(), caseNumber: '04-444441 raq111'},
  {packetId: 3, packetName: '04-44444 # 1 Test packet Three', caseMatterDescription: new Date().toLocaleString(), caseNumber: '04-444441 raq'}
];

export class Data {
  constructor(private packet: PacketsComponent) {}
  changeTheme(item: string) {
    console.log('clikced');
  }
}
