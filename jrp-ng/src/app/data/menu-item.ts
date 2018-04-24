export class MenuItem {
  name: string;
  code: string;
  divider?: boolean;
  navigateTo?: string;
  icon?: string;
  children?: MenuItem[];
}
