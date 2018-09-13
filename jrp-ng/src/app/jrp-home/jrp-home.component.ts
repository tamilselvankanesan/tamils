import {BreadCrumbService} from '../bread-crumb/bread-crumb.service';
import {Packet} from '../dto/packet';
import {Time} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, PRIMARY_OUTLET, NavigationEnd} from '@angular/router';
import {MenuItem} from 'primeng/api';
import 'rxjs/add/operator/filter';
import { PacketsService } from '../packets/packets.service';

@Component({
  selector: 'app-home',
  templateUrl: './jrp-home.component.html',
  styleUrls: ['./jrp-home.component.css']
})
export class JrpHomeComponent implements OnInit {

  text = 'hello';
  packets: Packet[] = [];

  constructor(private activatedRoute: ActivatedRoute, private breadcrumbService: BreadCrumbService, private router: Router,
    private packetService: PacketsService) {
    this.text = new Date().toLocaleString();
    this.packets = this.packetService.packets;
  }

  ngOnInit() {

  }

  handleNavigation() {

  }

}
