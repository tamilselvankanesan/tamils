import {Country} from './country';
import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'countryfilter'
})
export class CountryFilterPipe implements PipeTransform {

  transform(countries: Country[], searchText: string): any {
    if (!searchText) {return countries; }
    searchText = searchText.toLowerCase();
    return countries.filter(country => country.name.toLowerCase().includes(searchText));
  }

}
