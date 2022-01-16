import { Component, OnInit } from '@angular/core';
import { FishingLessonReservationWithDateAsString } from '../model/fishing-lesson-reservation-with-date-as-string';
import { DatePipe } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { FishingLesson } from '../model/fishing-lesson';
import { FishingLessonFeedback } from '../model/fishing-lesson-feedback';
import { FishingLessonComplaint } from '../model/fishing-lesson-complaint';
import { InstructorService } from '../service/instructor.service';

@Component({
  selector: 'app-customer-past-fishing-lesson-reservations',
  templateUrl: './customer-past-fishing-lesson-reservations.component.html',
  styleUrls: ['./customer-past-fishing-lesson-reservations.component.css']
})
export class CustomerPastFishingLessonReservationsComponent implements OnInit {

  fishingLessonReservations : FishingLessonReservationWithDateAsString[] = []
  displayedColumns: string[] = ['name', 'startDate', 'endDate', 'cancel'];
  errorMessage : string  = '';
  pipe = new DatePipe('en-US'); 
  fishingLessonTemp! : FishingLesson
  feedback : FishingLessonFeedback = {
    id: 0,
    gradeFishingLesson: 5,
    noteFishingLesson: '',
    gradeOwner: 5,
    noteOwner: '',
    fishingLessonReservationId : 0
  }
  show : boolean = false
  complaint : FishingLessonComplaint = {
    id: 0,
    noteFishingLesson: '',
    noteOwner: '',
    fishingLessonReservationId: 0
  }
  
  constructor(private _instructorService: InstructorService, private _snackBar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.getPastReservationsForCustomerUsername();
  }


  getPastReservationsForCustomerUsername() {
    this._instructorService.getPastReservationsForCustomerUsername(localStorage.getItem('username') || '')
        .subscribe(data =>  {
                    this.fishingLessonReservations = data                    
                    for (let res of this.fishingLessonReservations) {
                      res.startDateTime = this.pipe.transform(new Date(res.startDateTime), 'medium') || ''
                      res.endDateTime = this.pipe.transform(new Date(res.endDateTime), 'medium') || ''
                    }
                    },
                   error => this.errorMessage = <any>error);   
  }

  showFeedbackAndComplaintInfo(res: FishingLessonReservationWithDateAsString) {
    this.feedback.fishingLessonReservationId = res.id
    this.feedback.gradeFishingLesson = 5
    this.feedback.noteFishingLesson = ''
    this.feedback.gradeOwner = 5
    this.feedback.noteOwner = ''
    this.complaint.fishingLessonReservationId = res.id
    this.complaint.noteFishingLesson = ''
    this.complaint.noteOwner = ''
    this.show = true
  }

  sendFeedback() {
      this._instructorService.sendFeedback(this.feedback)
            .subscribe(data =>  {},
                 error => this.errorMessage = <any>error); 
      this.show = false;
      this.router.navigateByUrl('/').then(() => {
        this._snackBar.open('Your feedback has been sent successfully!', 'Close', {duration: 5000});
      })      
  }

  sendComplaint() {
    this._instructorService.sendComplaint(this.complaint)
            .subscribe(data =>  {},
                 error => this.errorMessage = <any>error); 
      this.show = false;
      this.router.navigateByUrl('/').then(() => {
        this._snackBar.open('Your complaint has been sent successfully!', 'Close', {duration: 5000});
      })
  }

  sortData(sort: Sort) {
    const data = this.fishingLessonReservations.slice();
    if (!sort.active || sort.direction === '') {
      this.fishingLessonReservations = data;
      return;
    }

    this.fishingLessonReservations = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'houseName':
          return compare(a.fishingLesson.name, b.fishingLesson.name, isAsc);
        case 'startDate':
          return compare(new Date(a.startDateTime), new Date(b.startDateTime), isAsc);
        case 'endDate':
          return compare(new Date(a.endDateTime), new Date(b.endDateTime), isAsc);
        default:
          return 0;
      }
    });
  }
}

function compare(a: number | string | Date, b: number | string | Date, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }