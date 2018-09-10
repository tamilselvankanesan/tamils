import {SimplePerson} from './simple-person';
export class SearchCriteria {

  selectedJudges: SimplePerson[] = [];
  documentNumberFrom: number;
  documentNumberTo: number;
  documentFiledBy: string;
  terminalDigits: number;
}
