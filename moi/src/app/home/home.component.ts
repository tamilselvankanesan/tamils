import {User} from './user';
import {Component, OnInit} from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user = new User();

  constructor() {}

  ngOnInit() {
  }
  toggleSignupAndLogin(flag: boolean) {
    if (flag) {
      $('#loginbox').hide();
      $('#signupbox').show();
    } else {
      $('#signupbox').hide();
      $('#loginbox').show();
    }
  }

  signup() {

  }

}
