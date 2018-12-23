import { Contact } from "./contact.model";
import { TreeData } from "./tree-data.model";
import { LEntity } from "./l-entity.model";

export class OShip{
    businessUnit: TreeData[] = [];
    legalEntity = new LEntity();
    role?: string;
    primaryContact: Contact = new Contact();
    additionalContacts: Contact[] = [];
    notes?: string;
}