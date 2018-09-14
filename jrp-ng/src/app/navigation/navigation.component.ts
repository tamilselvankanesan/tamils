import {BreadCrumbService} from '../bread-crumb/bread-crumb.service';
import {Packet} from '../dto/packet';
import {PacketsService} from '../packets/packets.service';
import {JrpMenuEnum} from '../util/jrpmenuenum';
import {Time} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, PRIMARY_OUTLET, NavigationEnd} from '@angular/router';
import {MenuItem} from 'primeng/api';
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

      this.breadcrumbs = [{label: 'Home'}];
      if (urlString.indexOf(JrpMenuEnum.AdvancedSearch) !== -1) {
        console.log('advanced search');
        this.breadcrumbs.push({label: 'Packet Searching'});
        this.breadcrumbs.push({label: 'Search'});
        this.breadcrumbs.push({label: 'Advanced Search'});
      } else {
        this.breadcrumbs.push({label: this.text});
      }
      this.breadcrumbService.setBreadcrumb(this.breadcrumbs);
    });
  }
  retrievePackets() {
    console.log('retrieve packets');
    const index = Math.floor(Math.random() * (1000 - 100 + 1)) + 100;
    const packetsList: Packet[] = [
      {packetId: index + 1, packetName: '04-44444 # 1 Test packet One', caseMatterDescription: new Date().toLocaleString()},
      {packetId: index + 2, packetName: '04-44444 # 1 Test packet Two', caseMatterDescription: new Date().toLocaleString()},
      {packetId: index + 3, packetName: '04-44444 # 1 Test packet Three', caseMatterDescription: new Date().toLocaleString()}
    ];
    this.packetService.setPackets(packetsList);
    this.router.navigate(['home']);
  }
  ngOnInit() {
  }

}
