import {District} from './district';
import {DistrictService} from './district.service';
import {Component, Input, Output, EventEmitter, OnChanges, SimpleChanges, SimpleChange} from '@angular/core';

@Component({
  selector: 'app-district-list',
  templateUrl: './district-list.component.html'
})
export class DistrictListComponent implements OnChanges {
  @Input() list: District[];
  @Input() stateId: number;
  @Output() event = new EventEmitter();
  districts: District[];

  constructor(private districtService: DistrictService) {}

  ngOnChanges(changes: SimpleChanges): void {
    console.log('d changed');
    for (let propName in changes) {
      if (propName === 'stateId') {
        let changedProp = changes[propName];
        let to = JSON.stringify(changedProp.currentValue);
        if (to) {
          this.districtService.getDistricts(+to).subscribe(districts => this.districts = districts);
          console.log(to);
        }
      }
    }
  }

  onDistrictSelect(event) {
    this.event.emit(event);
  }
}
