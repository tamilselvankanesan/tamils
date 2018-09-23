import { EOShip } from "./e-o-ship.model";
import { OShip } from "./o-ship.model";

export class Rule{
    enhancedOwnershipSelected?: string;
    firstLineOwnership = new EOShip();
    secondLineOwnership = new EOShip();
    constructor(){}
    
    setupOwnershipData(){
        this.secondLineOwnership.ownerships.push(new OShip());
    }

}