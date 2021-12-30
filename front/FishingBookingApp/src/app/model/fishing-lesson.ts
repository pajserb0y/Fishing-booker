import { Instructor } from "./instructor";

export interface FishingLesson {
    id: number ;
    lessonName: string ;
    address: string ;
    details: string ;
    instructor: Instructor;
    grade: number ;
  }