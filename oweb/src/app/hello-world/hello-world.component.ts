import { Component, OnInit } from '@angular/core';
import {SelectItem} from 'primeng/primeng';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-hello-world',
  templateUrl: './hello-world.component.html',
  styleUrls: ['./hello-world.component.css']
})
export class HelloWorldComponent implements OnInit {
  types: SelectItem[];
  dangarousString = "";
  trustedString: SafeUrl;
  constructor(private sanitize: DomSanitizer) {

    this.dangarousString = "javascript:alert()";
    this.trustedString = this.sanitize.bypassSecurityTrustUrl(this.dangarousString);

    this.types = [
      {label:'Select City', value:null},
      {label:'New York', value:{id:1, name: 'New York', code: 'NY'}},
      {label:'Rome', value:{id:2, name: 'Rome', code: 'RM'}},
      {label:'London', value:{id:3, name: 'London', code: 'LDN'}},
      {label:'Istanbul', value:{id:4, name: 'Istanbul', code: 'IST'}},
      {label:'Paris', value:{id:5, name: 'Paris', code: 'PRS'}}
  ];
   }

  ngOnInit() {
  }

}
