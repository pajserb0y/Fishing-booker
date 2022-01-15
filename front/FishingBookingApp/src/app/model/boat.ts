import { AdditionalService } from "./additional-service";
import { BoatOwner } from "./boat-owner";
import { BoatReservation } from "./boat-reservation";
import { TermBoat } from "./term-boat";

export interface Boat {
    id: number ;
    name: string ;
    address: string ;
    description: string ;
    imagePath : string;
    freeTerms: TermBoat[];
    price: number; 
    rules: string;
    capacity: number
    additionalServices: AdditionalService[];
    boatOwner: BoatOwner;
    boatReservations: BoatReservation[];
    avgGrade: number;
  }