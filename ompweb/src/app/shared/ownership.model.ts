import { Contact } from "./contact.model";
import { TreeData } from "./tree-data.model";
import { LegalEntity } from "./legal-entity.model";

export class Ownership{
    businessUnit: TreeData[];
    legalEntity: LegalEntity;
    role?: string;
    primaryContact?: Contact;
    additionalContacts?: Contact[];
    notes?: string;
}