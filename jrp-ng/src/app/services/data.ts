import {PacketsComponent} from '../packets/packets.component';
import {MenuItem} from 'primeng/api';
import {RouterModule} from '@angular/router';

export const menuIems: MenuItem[] = [{
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
      routerLink: 'inbox/114',
      command: (event) => {}
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
        {label: 'Advanced', routerLink: 'inbox/211'},
        {label: 'Case/Party', routerLink: 'inbox/212'},
        {label: 'Date', routerLink: 'inbox/213'},
        {label: 'Packet', routerLink: 'inbox/214'}
      ]
    },
    {
      label: 'Hearing', routerLink: 'inbox/22',
      items: [{
        label: 'Judge Harmon',
        items: [{
          label: 'Today', routerLink: 'inbox/221'
        },
        {label: 'This Week', routerLink: 'inbox/222'}]
      }]
    },
    {label: 'Saved Search', routerLink: 'inbox/23'}
  ]
},
{
  label: 'Create',
  items: [
    {label: 'New Packet', routerLink: 'inbox/31'},
    {label: 'Other - Claims (BK only)', routerLink: 'inbox/32'},
    {label: 'Other - Miscellaneous', routerLink: 'inbox/33'}
  ]
},
{
  label: 'Configuration',
  items: [
    {label: 'My Configuration', routerLink: 'inbox/41'},
    {label: 'NextGen Configuration', routerLink: 'inbox/42'}
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

export class Data {
  constructor(private packet: PacketsComponent) {}
  changeTheme(item: string) {
    console.log('clikced');
  }
}
