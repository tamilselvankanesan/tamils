import { CountryService } from './country/country.service';
import { StateService } from './state/state.service';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [CountryService, StateService]
})
export class AppComponent implements OnInit {
  title = 'My NDB';
  ngOnInit(): void {
  }
}
