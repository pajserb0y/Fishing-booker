import { AdditionalService } from "./additional-service";
import { Customer } from "./customer";
import { FishingLesson } from "./fishing-lesson";

export interface FishingLessonReservationWithDateAsString {
    id: number ;
    startDateTime: string ;
    endDateTime: string;
    maxPeopleNumber: number ;
    startSpecialOffer: string ;
    endSpecialOffer: string | null;
    additionalServices: AdditionalService[];
    price: number;
    customer: Customer;
    fishingLesson: FishingLesson;
    cancelled: boolean;
  }