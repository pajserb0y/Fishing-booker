import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Sort } from '@angular/material/sort';
import { BoatReservationWithDateAsString } from '../model/boat-reservation-with-date-as-string';
import { BoatOwnerService } from '../service/boat-owner.service';

@Component({
  selector: 'app-customer-future-boat-reservations',
  templateUrl: './customer-future-boat-reservations.component.html',
  styleUrls: ['./customer-future-boat-reservations.component.css']
})
export class CustomerFutureBoatReservationsComponent implements OnInit {

  boatReservations : BoatReservationWithDateAsString[] = []
  displayedColumns: string[] = ['boatName', 'startDate', 'endDate', 'cancel'];
  errorMessage : string  = '';
  pipe = new DatePipe('en-US'); // Use your own locale

  constructor(private _boatOwnerService: BoatOwnerService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getFutureReservationsForCustomerUsername();
  }


  getFutureReservationsForCustomerUsername() {
    this._boatOwnerService.getFutureReservationsForCustomerUsername(localStorage.getItem('username') || '')
        .subscribe(data =>  {
                    this.boatReservations = data                    
                    for (let res of this.boatReservations) {
                      res.startDateTime = this.pipe.transform(new Date(res.startDateTime), 'medium') || ''
                      res.endDateTime = this.pipe.transform(new Date(res.endDateTime), 'medium') || ''
                    }
                    },
                   error => this.errorMessage = <any>error);   
  }

  cancelReservation(reservation : BoatReservationWithDateAsString) {
    this._boatOwnerService.cancelReservation(reservation.id)
        .subscribe(data => {},
                   error => this.errorMessage = <any>error);
    
    /* this.getFutureReservationsForCustomerUsername(); */
    reservation.cancelled = true;
    this._snackBar.open('Successfully cancelled', 'Close', {duration: 3000});
  }

  checkIsItMinThreeDaysBefore(reservation : BoatReservationWithDateAsString) {
    var start = new Date(reservation.startDateTime)
    var today = new Date();
    var todayPlusThree = new Date(today.setDate(today.getDate() + 3))
    if (start > todayPlusThree)
      return true
    return false
  }

  sortData(sort: Sort) {
    const data = this.boatReservations.slice();
    if (!sort.active || sort.direction === '') {
      this.boatReservations = data;
      return;
    }

    this.boatReservations = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'houseName':
          return compare(a.boat.name, b.boat.name, isAsc);
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
