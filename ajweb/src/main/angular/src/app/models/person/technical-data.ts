import { Experience } from './experience';
import { AdditionalCredential } from './additional-credential';

export class TechnicalData {
    experience?:           Experience[];
    coreCompetencies?:     string;
    technicalSkills?:      string;
    additionalCredential?: AdditionalCredential[];
}