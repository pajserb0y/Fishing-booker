import { keyframes } from '@angular/animations';
import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { BoatReservationWithDateAsString, Reservation } from '../model/boat-reservation-with-date-as-string';
import { CustomerReport } from '../model/customer-report';
import { FishingLessonReservationWithDateAsString } from '../model/fishing-lesson-reservation-with-date-as-string';
import { WeekendHouseReservationWithDateAsString } from '../model/weekend-house-reservation-with-date-as-string';
import { BoatOwnerService } from '../service/boat-owner.service';
import { InstructorService } from '../service/instructor.service';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';

@Component({
  selector: 'app-report-customers',
  templateUrl: './report-customers.component.html',
  styleUrls: ['./report-customers.component.css']
})
export class ReportCustomersComponent implements OnInit {

  reservation : Reservation = {
    id : 0,
    startDateTime : "",
    endDateTime : "",
    name : "",
    customer : "",
    cancelled : false
  };
  reservations : Reservation[] = [];
  role : string|null = localStorage.getItem('role');
  boatRreservations : BoatReservationWithDateAsString[] = [];
  weekendHouseReservations : WeekendHouseReservationWithDateAsString[] = [];
  fishingLessonReservations : FishingLessonReservationWithDateAsString[] = [];
  displayedColumns: string[] = ['name', 'startDate', 'endDate', 'cancelled', 'customer'];
  errorMessage : string  = '';
  pipe = new DatePipe('en-US');
  show : boolean = false;

  report : CustomerReport = {
    id : 0,
    reservationId : 0,
    comment : "",
    deservesPenalty : false,
    didntShowUp : false
  }


  constructor(private _weekendHouseOwnerService: WeekendHouseOwnerService, private _snackBar: MatSnackBar, private _boatOwnerService: BoatOwnerService, private _instructorService : InstructorService, private router: Router) { }

  ngOnInit(): void {
    if(this.role == 'ROLE_INSTRUCTOR')
        this.getAllFishingLessonReservationsForInstructor(localStorage.getItem("username") || "");
    else if (this.role == 'ROLE_BOAT_OWNER')
        this.getAllBoatReservationsForBoatOwner(localStorage.getItem("username") || "")   
    else if (this.role == 'ROLE_WEEKEND_HOUSE_OWNER')
        this.getAllWeekendHouseReservationsForHouseOwner(localStorage.getItem("username") || "");
  }

  getAllFishingLessonReservationsForInstructor(username : string) {
    this._instructorService.getAllReservationsForInstructor(username)
          .subscribe(data => { this.fishingLessonReservations =  data,
                      this.fishingLessonReservations = this.fishingLessonReservations.filter(flr => flr.customer != null),
                      this.convertLessonToReservation() },
                     error => this.errorMessage = <any>error); 
  }

  getAllBoatReservationsForBoatOwner(username : string) {
    this._boatOwnerService.getAllReservationsForBoatOwner(username)
          .subscribe(data => { this.boatRreservations = data,
                      this.boatRreservations = this.boatRreservations.filter(flr => flr.customer != null),
                      this.convertBoatToReservation() },
                     error => this.errorMessage = <any>error);
  }

  getAllWeekendHouseReservationsForHouseOwner(username : string) {
    this._weekendHouseOwnerService.getAllReservationsForHouseOwner(username)
          .subscribe(data => { this.weekendHouseReservations = data,
                      this.weekendHouseReservations = this.weekendHouseReservations.filter(flr => flr.customer != null),
                      this.convertHouseToReservation() },
                     error => this.errorMessage = <any>error);
  }

  convertHouseToReservation() { 
    this.reservations = []

    for (let res of this.weekendHouseReservations) {

      this.reservation.id = res.id;
      this.reservation.startDateTime =  res.startDateTime;
      this.reservation.endDateTime = res.endDateTime;
      this.reservation.name = res.weekendHouse.name ;
      this.reservation.cancelled = res.cancelled;
      this.reservation.customer = res.customer.firstName + ' ' + res.customer.lastName;

      let ress = Object.assign({}, this.reservation);
      this.reservations.push(ress);
    }
  
  }

  convertBoatToReservation() { 
    this.reservations = []

    for (let res of this.boatRreservations) {
      
      this.reservation.id = res.id;
      this.reservation.startDateTime =  res.startDateTime;
      this.reservation.endDateTime = res.endDateTime;
      this.reservation.name = res.boat.name ;
      this.reservation.cancelled = res.cancelled;
      this.reservation.customer = res.customer.firstName + ' ' + res.customer.lastName;

      let ress = Object.assign({}, this.reservation);
      this.reservations.push(ress);
    }
  
  }

  convertLessonToReservation() { 
    this.reservations = []
    
    for (let res of this.fishingLessonReservations) {
      
      this.reservation.id = res.id;
      this.reservation.startDateTime =  res.startDateTime;
      this.reservation.endDateTime = res.endDateTime;
      this.reservation.name = res.fishingLesson.name ;
      this.reservation.cancelled = res.cancelled;
      this.reservation.customer = res.customer.firstName + ' ' + res.customer.lastName;

      let ress = Object.assign({}, this.reservation);
      this.reservations.push(ress);
    }
  
  }

  showReportCustomer(res : Reservation) {
    this.report.reservationId = res.id

    this.show = true;
  }

  sendReport() {
    if(this.role == 'ROLE_INSTRUCTOR') {
      this._instructorService.submitReport(this.report)
          .subscribe(() =>  {},
                      error => this.errorMessage = <any>error); 
    } else if (this.role == 'ROLE_BOAT_OWNER') {
      this._boatOwnerService.submitReport(this.report)
          .subscribe(() =>  {},
                      error => this.errorMessage = <any>error); 
    } else if (this.role == 'ROLE_WEEKEND_HOUSE_OWNER') {
      this._weekendHouseOwnerService.submitReport(this.report)
          .subscribe(() =>  {},
                      error => this.errorMessage = <any>error); 
    }
     
    console.log(this.report)
    this.show = false;
    this.router.navigateByUrl('/').then(() => {
      this._snackBar.open('Your complaint has been sent successfully!', 'Close', {duration: 5000});
    })
  }

  sortData(sort: Sort) {
    const data = this.reservations.slice();
    if (!sort.active || sort.direction === '') {
      this.reservations = data;
      return;
    }

    this.reservations = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name':
          return compare(a.name, b.name, isAsc);
        case 'startDate':
          return compare(new Date(a.startDateTime), new Date(b.startDateTime), isAsc);
        case 'endDate':
          return compare(new Date(a.endDateTime), new Date(b.endDateTime), isAsc);
        case 'customer':
            return compare(a.customer, b.customer, isAsc);
        default:
          return 0;
      }
    });
  }

}

function compare(a: number | string | Date, b: number | string | Date, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}