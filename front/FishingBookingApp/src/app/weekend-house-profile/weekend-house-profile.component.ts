import { Component, OnInit } from '@angular/core';
import { AdditionalService } from '../model/additional-service';
import { WeekendHouse } from '../model/weekend-house';
import { WeekendHouseReservation } from '../model/weekend-house-reservation';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Customer } from '../model/customer'
import { FormControl, FormGroup } from '@angular/forms';
import { Term } from '../model/term';
@Component({
  selector: 'app-weekend-house-profile',
  templateUrl: './weekend-house-profile.component.html',
  styleUrls: ['./weekend-house-profile.component.css']
})
export class WeekendHouseProfileComponent implements OnInit {

  weekendHouse : WeekendHouse = 
  {
    id: 0,
    name: "",
    address: "",
    description: "",
    imagePath: "",
    bedNumber: 0,
    freeTerms: [],
    rules: "",
    price: 0,
    additionalServices: [],
    weekendHouseOwner : {
        id: 0,
        firstName: "",
        lastName: "",
        email: "",
        username:  "",
        password: "",
        address: "",
        city: "",
        country: "",
        phone: "",
        motive: ""},
    weekendHouseReservations: []
  }
  newService : AdditionalService = 
  {
    id: 0 ,
    name: '' ,
    price: 0
  }
  errorMessage : string  = '';
  selectedServices : AdditionalService [] = [];
  weekendHouseReservation !: WeekendHouseReservation;

  minDate = new Date();
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });
  newFreeTerm : Term = {
    startDateTime: new Date(), 
    endDateTime: new Date(),
    id: 0,
    weekendHouse : this.weekendHouse
  }

  allFreeTerms : Term[] = [];

  displayedColumns: string[] = ['startDateTime', 'endDateTime', 'price', 'customer', 'weekendHouse'];

  constructor(private _weekendHouseownerService: WeekendHouseOwnerService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
      this.weekendHouse = this._weekendHouseownerService.weekendHouse;
      this.getAllFreeTerms();
  }

  
  showUserInfo(weekendHouseReservation : WeekendHouseReservation)
  {
      this.weekendHouseReservation.customer = weekendHouseReservation.customer;
  }

  deleteService()
  {
    this.weekendHouse.additionalServices = this.weekendHouse.additionalServices.filter(item => !this.selectedServices.includes(item));
  }

  addNewService()
  {
    let newService  = Object.assign({},this.newService);
    this.weekendHouse.additionalServices.push(newService);
    this.newService.name = '';
    this.newService.price = 0;
  }
  editWeekendHouse()
  {
    this._weekendHouseownerService.editWeekendHouse(this.weekendHouse)
    .subscribe(data => {
      console.log('Dobio: ', data)
      if(data == null)
        this._snackBar.open('Incorrect filling of form! Check and send again edit request', 'Close', {duration: 5000});
      else
      {
        this.weekendHouse = data
      }
      },
    error => this.errorMessage = <any>error); 

console.log(this.weekendHouse);
this._snackBar.open('Successfully edited', 'Close', {duration: 5000});  
  }

  reserveWeekendHouse()
  {

  }
  addFreeTerm()
  {
    if(this.newFreeTerm.startDateTime < this.newFreeTerm.endDateTime)
        if(this.newFreeTerm.endDateTime > new Date()) //OVDE CE VEROVATNO TREBATI DODATI NEW FREE TERM I PRE SUBSCRIBA DA BI BILO ODMAH VIDLJIVO
          this._weekendHouseownerService.addFreeTerm(this.newFreeTerm)
          .subscribe(data =>  this.allFreeTerms.push(data),
            error => this.errorMessage = <any>error); 

  }

  getAllFreeTerms()
  {
       this._weekendHouseownerService.getAllFreeTermsForWeekendHouse(this.weekendHouse)
       .subscribe(data =>  this.allFreeTerms = data,
                  error => this.errorMessage = <any>error); 
  }
}
