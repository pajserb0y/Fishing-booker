import { Boat } from "./boat";

export interface TermBoat {
    startDateTime: Date;
    endDateTime: Date;
    id: number ;
    boat: Boat;
  }