import { AdditionalService } from "./additional-service";
import { Customer } from "./customer";
import { WeekendHouse } from "./weekend-house";

export interface WeekendHouseReservation {
    id: number ;
    startDateTime: Date ;
    endDateTime: Date;
    peopleNumber: number ;
    startSpecialOffer: Date | null ;
    endSpecialOffer: Date | null;
    services: AdditionalService[];
    price: number;
    customer: Customer | null;
    weekendHouse: WeekendHouse;
    cancelled: boolean;
  }