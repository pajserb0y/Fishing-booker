import { AdditionalService } from "./additional-service";
import { Customer } from "./customer";
import { WeekendHouse } from "./weekend-house";

export interface WeekendHouseReservationWithDateAsString {
    id: number ;
    startDateTime: string ;
    endDateTime: string ;
    peopleNumber: number ;
    startSpecialOffer: string ;
    endSpecialOffer: string ;
    services: AdditionalService[];
    price: number;
    customer: Customer;
    weekendHouse: WeekendHouse;
  }