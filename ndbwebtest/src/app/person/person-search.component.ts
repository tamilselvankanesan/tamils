import {Person} from './person';
import {PersonService} from './person.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'app-search',
  styleUrls: ['./person-search.component.css'],
  templateUrl: './person-search.component.html'
})
export class PersonSearchComponent implements OnInit {
  searchParam: string;
  searchResults: Person[];
  found = true;
  constructor(private personService: PersonService, private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.found = true;
    let paramFound = false;
    this.route.paramMap.switchMap((params: Params) => {
      if (params.get('param')) {
        paramFound = true;
        this.searchParam = params.get('param');
      }
      return this.personService.search(this.searchParam);
    }).subscribe(results => {
      this.searchResults = results;
      if (this.searchResults.length === 0 && paramFound) {
        this.found = false;
      }
    });
  }
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
