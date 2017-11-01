import {Person} from './person';
import {PersonService} from './person.service';
import {Component} from '@angular/core';

@Component({
  selector: 'app-search',
  styleUrls: ['./person-search.component.css'],
  templateUrl: './person-search.component.html'
})
export class PersonSearchComponent {
  searchParam: string;
  searchResults: Person[];
  found = true;
  constructor(private personService: PersonService) {}
  search() {
    this.found = true;
    if (this.searchParam.trim) {
      this.personService.search(this.searchParam).subscribe(results => {
        this.searchResults = results;
        if (this.searchResults.length === 0) {
          this.found = false;
        }
      });
    } else {
      this.searchResults = null;
      this.found = false;
    }
  }
}
