import { Address } from './address';
import { Language } from './language';
import { Education } from './education';
import { JobProfile } from './job-profile';

export class Person {
    personId?:   number;
    userId?:     number;
    firstName?:  string;
    middleName?: string;
    lastName?:   string;
    email?:      string;
    phone?:      string;
    address?:    Address;
    ethnicity?:  string;
    race?:       string[];
    active?:     string;
    language?:   Language[];
    education?:  Education[];
    jobProfile?: JobProfile;
}