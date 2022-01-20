import { AdditionalService } from "./additional-service";
import { Boat } from "./boat";
import { Customer } from "./customer";
import { FishingLesson } from "./fishing-lesson";
import { WeekendHouse } from "./weekend-house";

export interface BoatReservation {
    id: number ;
    startDateTime: Date ;
    endDateTime: Date;
    capacity: number ;
    startSpecialOffer: Date | null;
    endSpecialOffer: Date | null;
    services: AdditionalService[];
    price: number;
    customer: Customer | null;
    boat: Boat|WeekendHouse|FishingLesson;
    cancelled: boolean;
  }