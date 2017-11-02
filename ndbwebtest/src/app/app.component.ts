import {Component} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'My NDB Test workspace Home';
  searchParam: string;
  constructor(private router: Router) {}
  search() {
    console.log(this.searchParam);
    this.router.navigateByUrl('/search/' + this.searchParam);
  }
}
