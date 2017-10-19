import {District} from './district';
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

  ngOnChanges(changes: SimpleChanges): void {
    for (let propName in changes) {
      if (propName === 'stateId') {
        let changedProp = changes[propName];
        let to = JSON.stringify(changedProp.currentValue);
        console.log(to);
      }
    }
  }

  onDistrictSelect(event) {
    this.event.emit();
  }
}
