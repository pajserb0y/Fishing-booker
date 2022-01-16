import { FishingLesson } from "./fishing-lesson";

export interface TermFishingLesson {
    startDateTime: Date;
    endDateTime: Date;
    id: number ;
    fishingLesson: FishingLesson;
  }