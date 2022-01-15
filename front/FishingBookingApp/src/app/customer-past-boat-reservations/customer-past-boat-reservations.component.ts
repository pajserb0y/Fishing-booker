import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { Boat } from '../model/boat';
import { BoatComplaint } from '../model/boat-complaint';
import { BoatFeedback } from '../model/boat-feedback';
import { BoatReservationWithDateAsString } from '../model/boat-reservation-with-date-as-string';
import { BoatOwnerService } from '../service/boat-owner.service';
import { MatGridListModule} from '@angular/material/grid-list';

@Component({
  selector: 'app-customer-past-boat-reservations',
  templateUrl: './customer-past-boat-reservations.component.html',
  styleUrls: ['./customer-past-boat-reservations.component.css']
})
export class CustomerPastBoatReservationsComponent implements OnInit {

  boatReservations : BoatReservationWithDateAsString[] = []
  displayedColumns: string[] = ['boatName', 'startDate', 'endDate', 'cancel'];
  errorMessage : string  = '';
  pipe = new DatePipe('en-US'); 
  boatTemp! : Boat
  feedback : BoatFeedback = {
    id: 0,
    gradeBoat: 5,
    noteBoat: '',
    gradeOwner: 5,
    noteOwner: '',
    boatReservationId : 0
  }
  show : boolean = false
  complaint : BoatComplaint = {
    id: 0,
    noteBoat: '',
    noteOwner: '',
    boatReservationId: 0
  }

  constructor(private _boatOwnerService: BoatOwnerService, private _snackBar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.getPastReservationsForCustomerUsername();
  }


  getPastReservationsForCustomerUsername() {
    this._boatOwnerService.getPastReservationsForCustomerUsername(localStorage.getItem('username') || '')
        .subscribe(data =>  {
                    this.boatReservations = data                    
                    for (let res of this.boatReservations) {
                      res.startDateTime = this.pipe.transform(new Date(res.startDateTime), 'medium') || ''
                      res.endDateTime = this.pipe.transform(new Date(res.endDateTime), 'medium') || ''
                    }
                    },
                   error => this.errorMessage = <any>error);   
  }

  showFeedbackAndComplaintInfo(res: BoatReservationWithDateAsString) {
    this.feedback.boatReservationId = res.id
    this.feedback.gradeBoat = 5
    this.feedback.noteBoat = ''
    this.feedback.gradeOwner = 5
    this.feedback.noteOwner = ''
    this.complaint.boatReservationId = res.id
    this.complaint.noteBoat = ''
    this.complaint.noteOwner = ''
    this.show = true
  }

  sendFeedback() {
      this._boatOwnerService.sendFeedback(this.feedback)
            .subscribe(data =>  {},
                 error => this.errorMessage = <any>error); 
      this.show = false;
      this.router.navigateByUrl('/').then(() => {
        this._snackBar.open('Your feedback has been sent successfully!', 'Close', {duration: 5000});
      })      
  }

  sendComplaint() {
    this._boatOwnerService.sendComplaint(this.complaint)
            .subscribe(data =>  {},
                 error => this.errorMessage = <any>error); 
      this.show = false;
      this.router.navigateByUrl('/').then(() => {
        this._snackBar.open('Your complaint has been sent successfully!', 'Close', {duration: 5000});
      })
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