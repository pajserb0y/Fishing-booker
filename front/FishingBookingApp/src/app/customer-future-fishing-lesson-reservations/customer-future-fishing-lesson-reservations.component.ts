import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Sort } from '@angular/material/sort';
import { FishingLessonReservationWithDateAsString } from '../model/fishing-lesson-reservation-with-date-as-string';
import { InstructorService } from '../service/instructor.service';

@Component({
  selector: 'app-customer-future-fishing-lesson-reservations',
  templateUrl: './customer-future-fishing-lesson-reservations.component.html',
  styleUrls: ['./customer-future-fishing-lesson-reservations.component.css']
})
export class CustomerFutureFishingLessonReservationsComponent implements OnInit {

  fishingLessonReservations : FishingLessonReservationWithDateAsString[] = []
  displayedColumns: string[] = ['name', 'startDate', 'endDate', 'cancel'];
  errorMessage : string  = '';
  pipe = new DatePipe('en-US'); // Use your own locale

  constructor(private _instructorService: InstructorService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getFutureReservationsForCustomerUsername();
  }

  getFutureReservationsForCustomerUsername() {
    this._instructorService.getFutureReservationsForCustomerUsername(localStorage.getItem('username') || '')
        .subscribe(data =>  {
                    this.fishingLessonReservations = data                    
                    for (let res of this.fishingLessonReservations) {
                      res.startDateTime = this.pipe.transform(new Date(res.startDateTime), 'medium') || ''
                      res.endDateTime = this.pipe.transform(new Date(res.endDateTime), 'medium') || ''
                    }
                    },
                   error => this.errorMessage = <any>error);   
  }

  cancelReservation(reservation : FishingLessonReservationWithDateAsString) {
    this._instructorService.cancelReservation(reservation.id)
        .subscribe(data => {},
                   error => this.errorMessage = <any>error);
    
    /* this.getFutureReservationsForCustomerUsername(); */
    reservation.cancelled = true;
    this._snackBar.open('Successfully cancelled', 'Close', {duration: 3000});
  }

  checkIsItMinThreeDaysBefore(reservation : FishingLessonReservationWithDateAsString) {
    var start = new Date(reservation.startDateTime)
    var today = new Date();
    var todayPlusThree = new Date(today.setDate(today.getDate() + 3))
    if (start > todayPlusThree)
      return true
    return false
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

