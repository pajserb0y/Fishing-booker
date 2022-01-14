import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { HouseFeedback } from '../model/house-feedback';
import { WeekendHouse } from '../model/weekend-house';
import { WeekendHouseReservation } from '../model/weekend-house-reservation';
import { WeekendHouseReservationWithDateAsString } from '../model/weekend-house-reservation-with-date-as-string';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';

@Component({
  selector: 'app-customer-past-weekend-house-reservations',
  templateUrl: './customer-past-weekend-house-reservations.component.html',
  styleUrls: ['./customer-past-weekend-house-reservations.component.css']
})
export class CustomerPastWeekendHouseReservationsComponent implements OnInit {

  houseReservations : WeekendHouseReservationWithDateAsString[] = []
  displayedColumns: string[] = ['houseName', 'startDate', 'endDate', 'cancel'];
  errorMessage : string  = '';
  pipe = new DatePipe('en-US'); 
  houseTemp! : WeekendHouse
  feedback : HouseFeedback = {
    id: 0,
    gradeHouse: 5,
    noteHouse: '',
    gradeOwner: 5,
    noteOwner: '',
    weekendHouseReservationId : 0
  }
  show : boolean = false


  constructor(private _weekendHouseOwnerService: WeekendHouseOwnerService, private _snackBar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.getPastReservationsForCustomerUsername();
  }


  getPastReservationsForCustomerUsername() {
    this._weekendHouseOwnerService.getPastReservationsForCustomerUsername(localStorage.getItem('username') || '')
        .subscribe(data =>  {
                    this.houseReservations = data                    
                    for (let res of this.houseReservations) {
                      res.startDateTime = this.pipe.transform(new Date(res.startDateTime), 'medium') || ''
                      res.endDateTime = this.pipe.transform(new Date(res.endDateTime), 'medium') || ''
                    }
                    },
                   error => this.errorMessage = <any>error);   
  }

  showFeedbackInfo(res: WeekendHouseReservationWithDateAsString) {
    this.feedback.weekendHouseReservationId = res.id
    this.feedback.gradeHouse = 5
    this.feedback.noteHouse = ''
    this.feedback.gradeOwner = 5
    this.feedback.noteOwner = ''
    this.show = true
  }

  sendFeedback() {
      this._weekendHouseOwnerService.sendFeedback(this.feedback)
            .subscribe(data =>  {},
                 error => this.errorMessage = <any>error); 
      this.show = false;
      this.router.navigateByUrl('/').then(() => {
        this._snackBar.open('Your feedback has been sent successfully!', 'Close', {duration: 5000});
      })      
  }

  sortData(sort: Sort) {
    const data = this.houseReservations.slice();
    if (!sort.active || sort.direction === '') {
      this.houseReservations = data;
      return;
    }

    this.houseReservations = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'houseName':
          return compare(a.weekendHouse.name, b.weekendHouse.name, isAsc);
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