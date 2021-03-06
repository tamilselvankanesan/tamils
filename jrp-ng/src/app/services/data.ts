import { SimplePerson } from '../dto/simple-person';
import { PacketsComponent } from '../packets/packets.component';
import { MenuItem } from 'primeng/api';
import { RouterModule } from '@angular/router';
import { ComponentConfigValue } from '../dto/component-config-value';
import { Packet } from '../dto/packet';
import { Person } from '../dto/person';

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
        items: [{ label: 'Test', routerLink: 'inbox/1211' }]
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
          { label: 'Advanced', routerLink: 'search/211' },
          { label: 'Case/Party', routerLink: 'search/212' },
          { label: 'Date', routerLink: 'search/213' },
          { label: 'Packet', routerLink: 'search/214' }
        ]
      },
      {
        label: 'Hearing', routerLink: 'search/22',
        items: [{
          label: 'Judge Harmon',
          items: [{
            label: 'Today', routerLink: 'search/221'
          },
          { label: 'This Week', routerLink: 'search/222' }]
        }]
      },
      { label: 'Saved Search', routerLink: 'search/23' }
    ]
  },
  {
    label: 'Create',
    items: [
      { label: 'New Packet', routerLink: 'create/31' },
      { label: 'Other - Claims (BK only)', routerLink: 'create/32' },
      { label: 'Other - Miscellaneous', routerLink: 'create/33' }
    ]
  },
  {
    label: 'Configuration',
    items: [
      { label: 'My Configuration', routerLink: 'configuration/41' },
      { label: 'NextGen Configuration', routerLink: 'configuration/42' },
      { label: 'Auto Packets Configuration', routerLink: 'configuration/43' }
    ]
  },
  {
    label: 'About',
    items: [
      { label: 'About', routerLink: 'inbox/51' },
      { label: 'Tutorial', routerLink: 'inbox/52' }
    ]
  }
];


export const judges: SimplePerson[] = [
  { id: 1, name: 'Aguila, Ricky Middle, II' },
  { id: 2, name: 'AJTA, JudgeTwoB' }
];

export const judgesList: Person[] = [
  { prid: 1, fullName: 'Aguila, Ricky Middle, II' },
  { prid: 2, fullName: 'AJTA, JudgeTwoB' }
];

export const columnSettingsConfig: ComponentConfigValue[] = [
  { userInterfaceScreenFieldKey: 'panelPacketListColumn_packetName', genericStringValue: 'ASC' },
  { userInterfaceScreenFieldKey: 'panelPacketListColumn_caseMatterDescription', genericStringValue: 'ASC' },
  { userInterfaceScreenFieldKey: 'panelPacketListColumn_caseNumber', genericStringValue: 'ASC' },

  { userInterfaceScreenFieldKey: 'panelPacketListItemsColumn_caseDocNum', genericStringValue: 'ASC' },
  { userInterfaceScreenFieldKey: 'panelPacketListItemsColumn_caseMatterDescription', genericStringValue: 'ASC' },
  { userInterfaceScreenFieldKey: 'panelPacketListItemsColumn_caseNumber', genericStringValue: 'ASC' },

  { userInterfaceScreenFieldKey: 'panelPacketListNotesColumn_subject', genericStringValue: 'ASC' },
  { userInterfaceScreenFieldKey: 'panelPacketListNotesColumn_notesEntry', genericStringValue: 'ASC' },
  { userInterfaceScreenFieldKey: 'panelPacketListNotesColumn_dateEntered', genericStringValue: 'ASC' },

  { userInterfaceScreenFieldKey: 'panelPacketListFilesColumn_fileName', genericStringValue: 'ASC' },
  { userInterfaceScreenFieldKey: 'panelPacketListFilesColumn_fileDescription', genericStringValue: 'ASC' },
  { userInterfaceScreenFieldKey: 'panelPacketListFilesColumn_fileCreatedDate', genericStringValue: 'ASC' },
  { userInterfaceScreenFieldKey: 'panelPacketListFilesColumn_fileCreator', genericStringValue: 'ASC' }
];

export const sampleData: Packet[] = [
  {
    packetId: 2, packetName: '04-44444 # 2 Test packet One Twenty', caseMatterDescription: new Date().toLocaleString(),
    caseNumber: '04-55555 raq11ddd',
    notes: [
      {
        notesEntry: 'Test Note 111',
        dateEntered: '01/01/2019',
        userName: 'Tamilselvan B'
      }
    ],
    files: [
      {
        fileName: 'Test.pdf',
        fileDescription: 'Hello file',
        fileCreatedDate: '01/01/2019',
        fileCreator: 'Tamilselvan B'
      }
    ],
    events: [
      {
        caseNumber: '04-44444 Test Case NUmber 1',
        caseMatterDescription: 'Motion to Compel',
        caseMatterFiledBy: 'Tamilselvan Balasubramaniam',
        caseMatterFiledDate: '01/01/2018',
        caseDocNum: 1
      },
      {
        caseNumber: '04-44446 Test Case NUmber 1',
        caseMatterDescription: 'Motion to Reconsider',
        caseMatterFiledBy: 'Tamilselvan Balasubramaniam',
        caseMatterFiledDate: '02/01/2018',
        caseDocNum: 120
      }
    ],
  },
  {
    packetId: 1, packetName: '04-44444 # 1 Test packet One Twenty', caseMatterDescription: new Date().toLocaleString(),
    caseNumber: '04-444441 raq11ddd',
    notes: [
      {
        notesEntry: 'Test Note 111',
        dateEntered: '01/01/2019',
        userName: 'Tamilselvan B'
      }
    ],
    files: [
      {
        fileName: 'Test.pdf',
        fileDescription: 'Hello file',
        fileCreatedDate: '01/01/2019',
        fileCreator: 'Tamilselvan B'
      }
    ],
    events: [
      {
        caseNumber: '04-44444 Test Case NUmber 1',
        caseMatterDescription: 'Motion to Compel',
        caseMatterFiledBy: 'Tamilselvan Balasubramaniam',
        caseMatterFiledDate: '01/01/2018',
        caseDocNum: 1
      },
      {
        caseNumber: '04-44446 Test Case NUmber 1',
        caseMatterDescription: 'Motion to Reconsider',
        caseMatterFiledBy: 'Tamilselvan Balasubramaniam',
        caseMatterFiledDate: '02/01/2018',
        caseDocNum: 120
      }
    ],
  }
];

export const apcModels = [{
  rfstId: '',
  enabled: false,
  eventName: 'Motion to Compel',
  eventDescription: `Factor 1 If a "Motion to Compel" is filed <br/>
                    Factor 2 If an Electronic Packet has not been created for the filing <br/>
                    Action 1 Then the JRP will Create a new Packet <br/>
                    Action 2 The primary event for the JRP will be the event that triggers the creation of the packet <br/>
                    Action 3 The JRP will use the following configuration. Priority "Urgent", Status "Reviewed", Routing "SysadminBKZ Balasubramaniam" and Due date "2 Days"`,
  editable: true,
  deletable: true
},
{
  rfstId: '',
  enabled: false,
  eventName: 'Motion',
  eventDescription: `Factor 1 If a MOTION is filed <br/>
  Factor 2 If an Electronic Packet has not been created for the filing <br/>
  Action 1 Then the JRP will Create a new Packet <br/>
  Action 2 The primary event for the JRP will be the event that triggers the creation of the packet <br/>
  Action 3 The JRP will use the following configuration. Priority "Normal", Status "New" and Routing "Judge C. Blue" <br/>
  Action 4 The JRP will Create Additional Filing Rules so that when additional filings are docketed to the case the following should happen: <br/>
  Action 5 The JRP will add to the packet all Additional Filings that have been directly or indirectly linked to the primary/triggering event <br/>
  Action 6 The JRP will use the following configuration. Priority "Normal", Status "New", Routing "Currently routed to" and Due date "2 Days"`,
  editable: true,
  deletable: true
}
];

export class Data {
  constructor(private packet: PacketsComponent) { }
  changeTheme(item: string) {
    console.log('clikced');
  }
}
