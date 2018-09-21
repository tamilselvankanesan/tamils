import { Component } from '@angular/core';
import { Rule } from './shared/r-model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  rule: Rule;
  constructor(){
    this.rule = new Rule();
  }
}
