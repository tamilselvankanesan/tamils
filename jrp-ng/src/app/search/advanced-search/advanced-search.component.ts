import {SearchCriteria} from '../../dto/search-criteria';
import {SimplePerson} from '../../dto/simple-person';
import {JrpService} from '../../services/jrp.service';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-advanced-search',
  templateUrl: './advanced-search.component.html',
  styleUrls: ['./advanced-search.component.css']
})
export class AdvancedSearchComponent implements OnInit {

  criteria = new SearchCriteria();
  jduges: SimplePerson[] = [];

  constructor(private jrpService: JrpService) {
    this.jrpService.getJudges().subscribe(data => this.jduges = data);
  }

  ngOnInit() {
  }

}
