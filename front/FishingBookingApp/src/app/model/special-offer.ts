import { AdditionalService } from "./additional-service";
import { Boat } from "./boat";
import { Customer } from "./customer";
import { FishingLesson } from "./fishing-lesson";
import { WeekendHouse } from "./weekend-house";

export interface SpecialOffer {
    id: number ;
    startDateTime: string ;
    endDateTime: string ;
    peopleNumber: number ;
    startSpecialOffer: string ;
    endSpecialOffer: string ;
    services: AdditionalService[];
    originalPrice: number;
    discountPrice: number;
    customer: Customer;
    entity: WeekendHouse|Boat|FishingLesson;
    entityType: string;
    cancelled: boolean;
  }