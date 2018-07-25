import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'moiSearch'
})
export class MoiSearchPipe implements PipeTransform {

  transform(data: any[], field: string, value: string): any[] {
    if (!data) {
      return [];
    }
    if (!field || !value) {
      return data;
    }
    return data.filter(item => item[field].toLowerCase().includes(value.toLowerCase()));
  }

}
