import { Personality } from './personality';
import { TechnicalData } from './technical-data';

export class JobProfile {
    field?:             string;
    title?:             string;
    headline?:          string;
    yrsOfExp?:          number;
    willingToRelocate?: string;
    jobPreference?:     string;
    travel?:            number;
    resume?:            string;
    awards?:            string[];
    volunteerism?:      string[];
    motivations?:       string[];
    goals?:             string;
    personality?:       Personality[];
    preferredOS?:       string[];
    preferredMobileOS?: string[];
    technicalData?:     TechnicalData;
}