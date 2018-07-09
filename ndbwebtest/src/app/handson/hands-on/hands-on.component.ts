import {AdminService} from '../../admin/admin.service';
import {Country} from '../../country/country';
import {CountryService} from '../../country/country.service';
import {State} from '../../state/state';
import {ApplicationUser} from '../../user/application-user';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-hands-on',
  templateUrl: './hands-on.component.html',
  styleUrls: ['./hands-on.component.css']
})
export class HandsOnComponent implements OnInit {

  countries: Country[];
  states: State[];
  selectedCountry: Country;
  sampleText = 'Hello';
  countryArr = [
    new Country('IN', 'India', null),
    new Country('USA', 'United States of America', null)
  ];

  selectedCtry = this.countryArr[0];
  input1: string;
  input2: string;
  applicationUser = new ApplicationUser();

  constructor(private countryService: CountryService, private adminService: AdminService) {}

  ngOnInit() {
    this.countryService.getCountries().subscribe(countries => this.countries = countries);
    //    this.getUserInfo();
  }
  onInput1Change(event) {
    console.log('input 1 changed');
    this.input2 = event;
  }
  login() {
    console.log(this.applicationUser.applicationLogin);
    this.adminService.login(this.applicationUser);
  }
  getUserInfo() {
    this.adminService.getCountryInfo('IN').subscribe(states => this.states = states);
  }
}
