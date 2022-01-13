import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { WeekendHouseReservation } from '../model/weekend-house-reservation';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';

@Component({
  selector: 'app-customer-future-weekend-house-reservations',
  templateUrl: './customer-future-weekend-house-reservations.component.html',
  styleUrls: ['./customer-future-weekend-house-reservations.component.css']
})
export class CustomerFutureWeekendHouseReservationsComponent implements OnInit {

  houseReservations : WeekendHouseReservation[] = []
  displayedColumns: string[] = ['houseName', 'startDate', 'endDate'];
  errorMessage : string  = '';
  startDate : string|null = '';
  endDate : string|null = '';
  pipe = new DatePipe('en-US'); // Use your own locale

  constructor(private _weekendHouseOwnerService: WeekendHouseOwnerService) { }

  ngOnInit(): void {
    this.getFutureReservationsForCustomerUsername();
  }


  getFutureReservationsForCustomerUsername() {
    this._weekendHouseOwnerService.getFutureReservationsForCustomerUsername(localStorage.getItem('username') || '')
        .subscribe(data =>  {
                    this.houseReservations = data                    
                    for (let res of this.houseReservations) {
                      this.startDate = this.pipe.transform(new Date(res.startDateTime), 'medium')
                      this.endDate = this.pipe.transform(new Date(res.endDateTime), 'medium')
                    }
                    },
                   error => this.errorMessage = <any>error);   
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
          return compare(a.startDateTime, b.startDateTime, isAsc);
        case 'endDate':
          return compare(a.endDateTime, b.endDateTime, isAsc);
        default:
          return 0;
      }
    });
  }
}

function compare(a: number | string | Date, b: number | string | Date, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }
