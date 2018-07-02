import {BreadCrumbService} from '../bread-crumb/bread-crumb.service';
import {Packet} from '../dto/packet';
import {PacketsService} from './packets.service';
import {Time} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, PRIMARY_OUTLET, NavigationEnd} from '@angular/router';
import {MenuItem} from 'primeng/api';
import 'rxjs/add/operator/filter';

@Component({
  selector: 'app-packets',
  templateUrl: './packets.component.html',
  styleUrls: ['./packets.component.css']
})
export class PacketsComponent implements OnInit {

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
