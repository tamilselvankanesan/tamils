import { BreadCrumbService } from '../bread-crumb/bread-crumb.service';
import { PacketsService } from '../packets/packets.service';
import { JrpMenuEnum } from '../util/jrpmenuenum';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import 'rxjs/add/operator/filter';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  text = 'hello';
  breadcrumbs: MenuItem[] = [];

  constructor(private activatedRoute: ActivatedRoute, private breadcrumbService: BreadCrumbService, private router: Router,
    private packetService: PacketsService) {

    const urlString = '' + this.activatedRoute.snapshot['_routerState'].url;

    this.updateBreadCrumb();

    if (urlString.indexOf(JrpMenuEnum.AdvancedSearch) !== -1) {
      this.router.navigate(['AdvancedSearch']);
    } else if (urlString.indexOf(JrpMenuEnum.AutoPacketsConfiguration) !== -1) {
      this.router.navigate(['AutoPacketsConfiguration']);
    } else {
      this.retrievePackets();
    }
  }

  updateBreadCrumb() {
    this.activatedRoute.url.subscribe(params => {
      console.log('update breadcrumb');
      //      debugger;
      this.text = new Date().toLocaleString();
      const tree: ActivatedRoute[] = this.activatedRoute.pathFromRoot;
      tree.forEach(item => {
        console.log(item.outlet);
      });
      this.text = new Date().toLocaleString();

      const urlString = '' + this.activatedRoute.snapshot['_routerState'].url;
      console.log('url is ' + urlString);

      this.breadcrumbs = [{ label: 'Home' }];
      if (urlString.indexOf(JrpMenuEnum.AdvancedSearch) !== -1) {
        console.log('advanced search');
        this.breadcrumbs.push({ label: 'Packet Searching' });
        this.breadcrumbs.push({ label: 'Search' });
        this.breadcrumbs.push({ label: 'Advanced Search' });
      } else if (urlString.indexOf(JrpMenuEnum.AutoPacketsConfiguration) !== -1) {
        console.log('Automatic Packets configuration');
        this.breadcrumbs.push({ label: 'Configuration' });
        this.breadcrumbs.push({ label: 'Auto Packets configuration' });
      } else {
        this.breadcrumbs.push({ label: this.text });
      }
      this.breadcrumbService.setBreadcrumb(this.breadcrumbs);
    });
  }
  retrievePackets() {
    console.log('retrieve packets based on menu selection');
    this.packetService.setPackets(this.packetService.getPackets());
    this.router.navigate(['home']);
  }
  ngOnInit() {
  }

}
