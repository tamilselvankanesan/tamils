import {MoiGroup} from '../moi-home/moi-group';
import {MoiData} from './moidata';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';

const moiGroup: MoiGroup[] = [
  {groupId: 1, groupName: 'Marurpatti Group 1', groupCreatedDate: Date.now.toString(), groupOwnerId: 12},
  {groupId: 2, groupName: 'Marurpatti Group 2', groupCreatedDate: Date.now.toString(), groupOwnerId: 124},
  {groupId: 3, groupName: 'Marurpatti Group 3', groupCreatedDate: Date.now.toString(), groupOwnerId: 144}
];

@Injectable()
export class MoiMainService {

  datas: MoiData[] = [];

  constructor() {}

  getAllData(): MoiData[] {
    this.datas = [
      {id: 1, name: 'Tamilselvan', amount: 100, address: 'Marurpatti', town: 'Namakkal'}
    ];
    return this.datas;
  }

  saveData(ip: MoiData) {

  }

  getData(id: number): Observable<MoiData> {
    let data: MoiData = {
      id: id,
      name: 'Tamilselvan ' + id,
      amount: 1000 + id,
      address: 'Marurpatti ' + id,
      town: 'Namakkal ' + id,
      editable: false
    };
    const ob = new Observable((observer) => {
      observer.next(data);
      observer.complete();
    });
    return ob;
  }
  getGroups(userId: number): Observable<MoiGroup[]> {
    return Observable.of(moiGroup);
  }
}
