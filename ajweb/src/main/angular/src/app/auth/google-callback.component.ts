import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-google-callback',
  templateUrl: './google-callback.component.html'
})
export class GoogleCallbackComponent implements OnInit {

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    const code = this.route.snapshot.queryParamMap.get("code");
    console.log('googel code is '+code);
  }

}
