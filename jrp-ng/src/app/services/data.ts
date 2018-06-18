import { MenuItem } from 'primeng/api';
export const menuIems: MenuItem[] = [{
                label: 'File',
                icon: 'fa fa-fw fa-file-o',
                items: [{
                        label: 'New',
                        icon: 'fa fa-fw fa-plus',
                        items: [
                            {label: 'Project'},
                            {label: 'Other'},
                        ]
                    },
                    {label: 'Open'},
                    {separator: true},
                    {label: 'Quit'}
                ]
            },
            {
                label: 'Edit',
                icon: 'fa fa-fw fa-edit',
                items: [
                    {label: 'Undo', icon: 'fa fa-fw fa-mail-forward'},
                    {label: 'Redo', icon: 'fa fa-fw fa-mail-reply'}
                ]
            }
  ];

export class Data {

}
