import { AdditionalService } from "./additional-service";
import { WeekendHouseTerm } from "./term-weekend-house";
import { WeekendHouseOwner } from "./weekend-house-owner";
import { WeekendHouseReservation } from "./weekend-house-reservation";

export interface WeekendHouse {
    id: number ;
    name: string ;
    address: string ;
    description: string ;
    /* grade: number ;   ovo se dobavlja iz tabele svih ocena*/
    imagePath : string[];
    bedNumber: number ;
    freeTerms: WeekendHouseTerm[];
    rules: string;
    price: number;
    additionalServices: AdditionalService[];
    weekendHouseOwner: WeekendHouseOwner;
    weekendHouseReservations: WeekendHouseReservation[];
  }