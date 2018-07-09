import {City} from './city';
import {CityService} from './city.service';
import {Component, OnChanges, SimpleChanges, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-city-dropdown',
  templateUrl: './city-dropdown.component.html'
})
export class CityDropdownComponent implements OnChanges {

  @Input() districtId: number;
  @Output() citySelect = new EventEmitter();
  cities: City[];

  constructor(private cityService: CityService) {}

  ngOnChanges(changes: SimpleChanges): void {
    for (let propName in changes) {
      let to = JSON.stringify(changes[propName].currentValue);
      if (to) {
        this.cityService.getCities(+to).subscribe(cities => this.cities = cities);
      }
    }
  }
  onCityChange(event) {
    this.citySelect.emit(event);
  }
}
