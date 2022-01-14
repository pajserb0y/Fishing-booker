import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Sort } from '@angular/material/sort';
import { elementAt } from 'rxjs-compat/operator/elementAt';
import { WeekendHouseReservation } from '../model/weekend-house-reservation';
import { WeekendHouseReservationWithDateAsString } from '../model/weekend-house-reservation-with-date-as-string';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';

@Component({
  selector: 'app-customer-future-weekend-house-reservations',
  templateUrl: './customer-future-weekend-house-reservations.component.html',
  styleUrls: ['./customer-future-weekend-house-reservations.component.css']
})
export class CustomerFutureWeekendHouseReservationsComponent implements OnInit {

  houseReservations : WeekendHouseReservationWithDateAsString[] = []
  displayedColumns: string[] = ['houseName', 'startDate', 'endDate', 'cancel'];
  errorMessage : string  = '';
  pipe = new DatePipe('en-US'); // Use your own locale

  constructor(private _weekendHouseOwnerService: WeekendHouseOwnerService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getFutureReservationsForCustomerUsername();
  }


  getFutureReservationsForCustomerUsername() {
    this._weekendHouseOwnerService.getFutureReservationsForCustomerUsername(localStorage.getItem('username') || '')
        .subscribe(data =>  {
                    this.houseReservations = data                    
                    for (let res of this.houseReservations) {
                      res.startDateTime = this.pipe.transform(new Date(res.startDateTime), 'medium') || ''
                      res.endDateTime = this.pipe.transform(new Date(res.endDateTime), 'medium') || ''
                    }
                    },
                   error => this.errorMessage = <any>error);   
  }

  cancelReservation(reservation : WeekendHouseReservationWithDateAsString) {
    this._weekendHouseOwnerService.cancelReservation(reservation.id)
        .subscribe(data => {},
                   error => this.errorMessage = <any>error);
    
    this.getFutureReservationsForCustomerUsername();
    /* reservation.cancelled = true; */
    this._snackBar.open('Successfully cancelled', 'Close', {duration: 3000});
  }

  checkIsItMinThreeDaysBefore(reservation : WeekendHouseReservationWithDateAsString) {
    var start = new Date(reservation.startDateTime)
    var today = new Date();
    var todayPlusThree = new Date(today.setDate(today.getDate() + 3))
    if (start > todayPlusThree)
      return true
    return false
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
