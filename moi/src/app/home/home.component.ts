import {UserService} from '../service/user.service';
import {User} from './user';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import * as $ from 'jquery';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user = new User();

  constructor(private userService: UserService, private router: Router) {}

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
    this.userService.signup(this.user).subscribe(data => {
      this.user = data;
    });
    this.router.navigate(['Moi-Home']);
  }

}
