import { Contact } from "./contact.model";
import { TreeData } from "./tree-data.model";
import { LEntity } from "./l-entity.model";

export class OShip{
    businessUnit: TreeData[];
    legalEntity: LEntity;
    role?: string;
    primaryContact?: Contact;
    additionalContacts?: Contact[];
    notes?: string;
}