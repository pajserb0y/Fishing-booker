import { Boat } from "./boat";
import { FishingLesson } from "./fishing-lesson";
import { WeekendHouse } from "./weekend-house";

export interface Customer {
    id: number ;
    firstName: string ;
    lastName: string ;
    email: string ;
    username: string;
    password: string ;
    address: string ;
    city: string ;
    country: string ;
    phone: string ;
    penals: number;
    subscribedWeekendHouses: WeekendHouse[];
    subscribedBoats: Boat[];
    subscribedFishingLessons: FishingLesson[];
    points: number ;
    category: string ;
    discount: number;
    version: number;
  }