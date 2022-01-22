import { Component, OnInit, ViewChild } from '@angular/core';
import { AdditionalService } from '../model/additional-service';
import { WeekendHouse } from '../model/weekend-house';
import { WeekendHouseReservation } from '../model/weekend-house-reservation';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormControl, FormGroup } from '@angular/forms';
import { WeekendHouseTerm } from '../model/term-weekend-house';
import { Router } from '@angular/router';
import { MatTable } from '@angular/material/table';
@Component({
  selector: 'app-weekend-house-profile',
  templateUrl: './weekend-house-profile.component.html',
  styleUrls: ['./weekend-house-profile.component.css']
})
export class WeekendHouseProfileComponent implements OnInit {
  @ViewChild(MatTable) myTable!: MatTable<any>;
  weekendHouse : WeekendHouse = 
  {
    id: 0,
    name: "",
    address: "",
    description: "",
    imagePath: [],
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
  allReservationsForWeekendHouse : WeekendHouseReservation[] = [];
  weekendHouseReservation  : WeekendHouseReservation = {
    id: 0,
    startDateTime: new Date,
    endDateTime: new Date,
    peopleNumber: 0,
    startSpecialOffer: new Date,
    endSpecialOffer: null,
    services: [],
    price: this.weekendHouse.price,
    customer: {
      id: 0,
      firstName: "",
      lastName: "",
      email: "",
      username: "",
      password: "",
      address: "",
      city: "",
      country: "",
      phone: "",
      penals: 0,
      subscribedWeekendHouses: [],
      subscribedBoats: [],
      subscribedFishingLessons: [],
      category: '',
      discount: 0,
      points: 0,
      version: 0
    },
    weekendHouse: this.weekendHouse,
    cancelled: false
  }
specialOffers : WeekendHouseReservation [] = [] ;
specialOffer  : WeekendHouseReservation = {
    id: 0,
    startDateTime: new Date,
    endDateTime: new Date,
    peopleNumber: 0,
    startSpecialOffer: null,
    endSpecialOffer: null,
    services: [],
    price: 0,
    customer: null ,
    weekendHouse: this.weekendHouse,
    cancelled: false
  }


  minDate = new Date();
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });
  rangeOffer = new FormGroup({
    startOffer: new FormControl(),
    endOffer: new FormControl(),
  });
  rangeDuration = new FormGroup({
    startDuration: new FormControl(),
    endDuration: new FormControl(),
  });
  rangeTerm = new FormGroup({
    startTerm: new FormControl(),
    endTerm: new FormControl(),
  });
  newFreeTerm : WeekendHouseTerm = {
    startDateTime: new Date(), 
    endDateTime: new Date(),
    id: 0,
    weekendHouse : this.weekendHouse
  }

  allFreeTerms : WeekendHouseTerm[] = [];

  displayedColumns: string[] = ['startDateTime', 'endDateTime', 'price', 'customer', 'weekendHouse'];

  constructor(private _weekendHouseownerService: WeekendHouseOwnerService, private _snackBar: MatSnackBar, private router: Router) { 
    
  }

  ngOnInit(): void {
      this.weekendHouse = this._weekendHouseownerService.weekendHouse;
      if(this.weekendHouse == null)
        this.router.navigateByUrl('weekend-houses');
      this.getAllReservationsForWeekendHouse();
      this.weekendHouseReservation.price = this.weekendHouse.price;
      this.specialOffer.price = this.weekendHouse.price
      this.getAllFreeTerms();
  }

  
  showUserInfo(weekendHouseReservation : WeekendHouseReservation)
  {
      this.weekendHouseReservation.customer = weekendHouseReservation.customer;
      this.weekendHouseReservation.endDateTime = new Date(weekendHouseReservation.endDateTime);
      this.weekendHouseReservation.startDateTime = new Date(weekendHouseReservation.startDateTime);
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
    let currentDate = new Date();
    if(this.weekendHouseReservation.startDateTime.getTime() <= currentDate.getTime() && currentDate.getTime() <= this.weekendHouseReservation.endDateTime.getTime()){

      this.weekendHouseReservation.weekendHouse = this.weekendHouse
      this.weekendHouseReservation.endDateTime = this.getDateFromDatePickerRange(this.range.value.end)
      this.weekendHouseReservation.startDateTime = this.getDateFromDatePickerRange(this.range.value.start)
      this.weekendHouseReservation.endSpecialOffer = null
      this.weekendHouseReservation.price = this.weekendHouse.price
  
    
        for (let service of this.weekendHouseReservation.services) {
          this.weekendHouseReservation.price += service.price
        }
        this._weekendHouseownerService.reserve(this.weekendHouseReservation)
              .subscribe(data => {
                if(data == null)
                  this._snackBar.open('Someone has reserved house in selected term before you. Please select other term.', 'Close', {duration: 4000});
                else {
                  this.allReservationsForWeekendHouse = data,
                  this.allReservationsForWeekendHouse =  this.allReservationsForWeekendHouse.filter(res => res.customer != null),
                  //this.myTable.renderRows(),
                  this._snackBar.open('Reservation successful', 'Close', {duration: 2000});
                }}
                ,error => this.errorMessage = <any>error);
        this.weekendHouseReservation.customer = null;
        this.range.value.start = null;
        this.range.value.end = null;
        this.weekendHouseReservation.services = [];
        this.weekendHouseReservation.price = 0;
    }
    else
        this._snackBar.open('Reservation is not possible to make if current reservation has expired!', 'Close', {duration: 4000});   
  }

  checkIfReservationAvailable()
  {

  }

  makeSpecialOffer()
  {
    this.specialOffer.weekendHouse = this.weekendHouse;
    this.specialOffer.endDateTime = this.getDateFromDatePickerRange(this.rangeOffer.value.endOffer);
    this.specialOffer.startDateTime = this.getDateFromDatePickerRange(this.rangeOffer.value.startOffer);
    this.specialOffer.startSpecialOffer = this.getDateFromDatePickerRange(this.rangeDuration.value.startDuration);
    this.specialOffer.endSpecialOffer = this.getDateFromDatePickerRange(this.rangeDuration.value.endDuration);  
    //for (let service of this.specialOffer.services) {
    //  this.specialOffer.price += service.price
    //}
    this._weekendHouseownerService.makeSpecialOffer(this.specialOffer)
          .subscribe(data =>  {
            if(data == null)
              this._snackBar.open('Someone has reserved house in selected term. Please select other term.', 'Close', {duration: 4000});
            else {
              this.specialOffers = data,     
              this.specialOffers = this.specialOffers.filter(offer => offer.customer == null),
              this._snackBar.open('Special offer created successfuly', 'Close', {duration: 5000});
            }},
           error => this.errorMessage = <any>error); 

    
    this.specialOffer.customer = null;
    this.rangeOffer.value.startOffer = null;
    this.rangeOffer.value.endOffer = null;
    this.rangeDuration.value.startDuration = null;
    this.rangeDuration.value.endDuration = null;
    this.specialOffer.services = [];
    this.specialOffer.price = 0;
  }

  getDateFromDatePickerRange(start: Date) : Date{
    return new Date(Date.UTC(start.getFullYear(), start.getMonth(), start.getDate(), start.getHours(), start.getMinutes()));
  }

  checkAvailability(){
    
  }

  calculateTotalPrice(ob : any)
  {
    let selectedService = ob.source.value;
    console.log(selectedService);
    if (ob.source._selected)
      this.weekendHouseReservation.price += selectedService.price
    else
      this.weekendHouseReservation.price -= selectedService.price
  }
  addFreeTerm()
  {
    this.newFreeTerm.startDateTime = this.rangeTerm.value.startTerm;
    this.newFreeTerm.endDateTime = this.rangeTerm.value.endTerm;
    this.newFreeTerm.weekendHouse = this.weekendHouse; 
    this._weekendHouseownerService.addFreeTerm(this.newFreeTerm)
          .subscribe(data =>{
            if(data == null)
              this._snackBar.open('Someone has reserved house in selected term. Please select other term.', 'Close', {duration: 5000});
            else {
              this.allFreeTerms = data,
              this._snackBar.open('Free term created successfuly', 'Close', {duration: 5000});
            }},
            error => this.errorMessage = <any>error); 
    this.rangeTerm.value.startTerm = null;
    this.rangeTerm.value.endTerm = null;          
  }

  getAllFreeTerms()
  {
       this._weekendHouseownerService.getAllFreeTermsForWeekendHouse(this.weekendHouse)
       .subscribe(data =>  this.allFreeTerms = data,
                  error => this.errorMessage = <any>error); 
  }

  getAllReservationsForWeekendHouse()
  {
    this._weekendHouseownerService.getAllReservationsForWeekendHouse(this.weekendHouse)
       .subscribe(data =>  {
                      this.allReservationsForWeekendHouse = data, 
                      this.allReservationsForWeekendHouse = this.allReservationsForWeekendHouse.filter(res => res.customer !== null)},
                  error => this.errorMessage = <any>error); 

  }

  removeWeekendHouse() {
    this._weekendHouseownerService.removeWeekendHouse(this.weekendHouse.id)
      .subscribe(data =>  { this.allFreeTerms = data,
                            this.router.navigateByUrl('weekend-houses');  
      },
                  error => this.errorMessage = <any>error); 

    
  }
 
}
