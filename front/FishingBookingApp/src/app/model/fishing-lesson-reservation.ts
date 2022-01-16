import { AdditionalService } from "./additional-service";
import { Boat } from "./boat";
import { Customer } from "./customer";
import { FishingLesson } from "./fishing-lesson";

export interface FishingLessonReservation {
    id: number ;
    startDateTime: Date ;
    endDateTime: Date;
    maxPeopleNumber: number ;
    startSpecialOffer: Date ;
    endSpecialOffer: Date | null;
    additionalServices: AdditionalService[];
    price: number;
    customer: Customer;
    fishingLesson: FishingLesson;
    cancelled: boolean;
  }