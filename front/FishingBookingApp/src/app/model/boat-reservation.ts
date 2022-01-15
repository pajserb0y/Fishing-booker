import { AdditionalService } from "./additional-service";
import { Boat } from "./boat";
import { Customer } from "./customer";

export interface BoatReservation {
    id: number ;
    startDateTime: Date ;
    endDateTime: Date;
    capacity: number ;
    startSpecialOffer: Date ;
    endSpecialOffer: Date | null;
    services: AdditionalService[];
    price: number;
    customer: Customer;
    boat: Boat;
    cancelled: boolean;
  }