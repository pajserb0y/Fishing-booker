import { AdditionalService } from "./additional-service";
import { Boat } from "./boat";
import { Customer } from "./customer";

export interface Reservation {
    id : number;
    name : string;
    startDateTime: string ;
    endDateTime: string;
    cancelled : boolean;
    customer : string;
}

export interface BoatReservationWithDateAsString {
    id: number ;
    startDateTime: string ;
    endDateTime: string;
    capacity: number ;
    startSpecialOffer: string ;
    endSpecialOffer: string;
    services: AdditionalService[];
    price: number;
    customer: Customer;
    boat: Boat;
    cancelled: boolean;
  }