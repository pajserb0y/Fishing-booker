import { AdditionalService } from "./additional-service";
import { FishingLessonReservation } from "./fishing-lesson-reservation";
import { Instructor } from "./instructor";
import { TermFishingLesson } from "./term-fishing-lesson";

export interface FishingLesson {
    id: number ;
    name: string ;
    address: string ;
    description: string ;
    imagePaths : string;
    freeTerms: TermFishingLesson[];
    price: number; 
    rules: string;
    maxNumberOfPeople: number;
    fishingEquipment: string;
    cancelConditions: string;
    additionalServices: AdditionalService[];  
    instructor: Instructor;
    fishingReservations: FishingLessonReservation[];
    avgGrade: number ;
  }