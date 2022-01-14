import { WeekendHouseReservation } from "./weekend-house-reservation";
import { WeekendHouseReservationWithDateAsString } from "./weekend-house-reservation-with-date-as-string";

export interface HouseFeedback {
    id: number ;
    gradeHouse: number ;
    noteHouse: string ;
    gradeOwner: number;
    noteOwner: string;
    weekendHouseReservationId: number;
  }