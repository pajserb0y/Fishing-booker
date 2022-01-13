import { WeekendHouse } from "./weekend-house";

export interface Term {
    startDateTime: Date;
    endDateTime: Date;
    id: number ;
    weekendHouse: WeekendHouse;
  }