import { MenuItem } from '../data/menu-item';
export const menuIems: MenuItem[] = [
    {
      code: '1',
      name: 'Inbox',
      children: [
        {
        code: '11',
        name: 'All'
        },
        {
        code: '12',
        name: 'Status',
        children: [
          {
            code: '121',
            name: 'New'
          },
          {
            code: '122',
            name: 'Updated Date',
            children: [
              {
                code: '1221',
                name: 'Today'
              },
              {
                code: '1222',
                name: 'This Week'
              }
            ]
          }
        ]
        }
      ]
    },
  {
    code: '2',
    name: 'Search',
    children: [
      {
        code: '21',
        name: 'Case/Party'
      },
      {
        code: '22',
        name: 'Date'
      },
      {
        code: '23',
        name: 'Advanced'
      }
    ]
  }
  ];

export class Data {

}
