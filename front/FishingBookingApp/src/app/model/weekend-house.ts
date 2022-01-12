import { AdditionalService } from "./additional-service";
import { Term } from "./term";
import { WeekendHouseOwner } from "./weekend-house-owner";
import { WeekendHouseReservation } from "./weekend-house-reservation";

export interface WeekendHouse {
    id: number ;
    name: string ;
    address: string ;
    description: string ;
    /* grade: number ;   ovo se dobavlja iz tabele svih ocena*/
    imagePath : string;
    bedNumber: number ;
    freeTerms: Term[];
    rules: string;
    price: number;
    additionalServices: AdditionalService[];
    weekendHouseOwner: WeekendHouseOwner;
    weekendHouseReservations: WeekendHouseReservation[];
  }