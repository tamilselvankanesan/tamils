import {Component, OnInit} from '@angular/core';

const COUNTRIES: Country[] = [
  {id: 1, name: 'India'},
  {id: 2, name: 'USA'},
  {id: 3, name: 'Russia'},
  {id: 4, name: 'China'}
];

export class Country {
  id: number;
  name: string;
}

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent implements OnInit {

  constructor() {}

  ngOnInit() {
  }

}
