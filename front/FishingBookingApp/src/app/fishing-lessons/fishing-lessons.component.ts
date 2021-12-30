import { Component, OnInit } from '@angular/core';
import { FishingLesson } from '../model/fishing-lesson';

@Component({
  selector: 'app-fishing-lessons',
  templateUrl: './fishing-lessons.component.html',
  styleUrls: ['./fishing-lessons.component.css']
})
export class FishingLessonsComponent implements OnInit {

  fishingLessons: FishingLesson[] = []
  displayedColumns: string[] = ['lessonName', 'address', 'details', 'instructor', 'grade'];

  constructor() { }

  ngOnInit(): void {
  }

}
