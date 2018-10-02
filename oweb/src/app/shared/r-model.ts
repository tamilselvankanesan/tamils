import { EOShip } from "./e-o-ship.model";
import { OShip } from "./o-ship.model";

export class Rule{

    ownershipdata: {
        enhancedOwnershipSelected?: string;
        firstLineOwnership?: EOShip;
        secondLineOwnership?: EOShip;
    } = {};
    
    constructor(){}
}