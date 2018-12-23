import { Injectable } from '@angular/core';
import 'rxjs/add/observable/of';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class REService {

  constructor() { }

  getSecondBUs(type: string){
    let data = [
      {
        item: 'BU1',
        text: 'B Unit 1'
      },
      {
        item: 'BU2',
        text: 'B Unit2'
      }
    ];
    return Observable.of(data);
  }

}
