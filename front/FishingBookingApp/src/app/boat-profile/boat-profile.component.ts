import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdditionalService } from '../model/additional-service';
import { Boat } from '../model/boat';
import { BoatReservation } from '../model/boat-reservation';
import { TermBoat } from '../model/term-boat';
import { BoatOwnerService } from '../service/boat-owner.service';

@Component({
  selector: 'app-boat-profile',
  templateUrl: './boat-profile.component.html',
  styleUrls: ['./boat-profile.component.css']
})
export class BoatProfileComponent implements OnInit {

  boat: Boat ={
    id: 0,
    name: '',
    address: '',
    description: '',
    type: '',
    length: 0,
    engineNumber: 0,
    horsePower: 0,
    maxSpeed: 0,
    imagePath: '',
    capacity: 0,
    freeTerms: [],
    rules: '',
    price: 0,
    additionalServices: [],
    boatOwner: {
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
      motive: ""
    },
    boatReservations: [],
    avgGrade: 0
  }
  newService : AdditionalService = 
  {
    id: 0 ,
    name: '' ,
    price: 0
  }
  errorMessage : string  = '';
  selectedServices : AdditionalService [] = [];
  allReservationsForBoat : BoatReservation[] = [];

  boatReservation  : BoatReservation = {
    id: 0,
    startDateTime: new Date,
    endDateTime: new Date,
    startSpecialOffer: new Date,
    endSpecialOffer: null,
    services: [],
    price: this.boat.price,
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
    boat: this.boat,
    cancelled: false,
    capacity: 0
  }
specialOffers : BoatReservation [] = [] ;
specialOffer  : BoatReservation = {
  id: 0,
  startDateTime: new Date,
  endDateTime: new Date,
  endSpecialOffer: null,
  services: [],
  price: 0,
  cancelled: false,
  capacity: 0,
  startSpecialOffer: null,
  customer: null,
  boat: this.boat
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
  newFreeTerm : TermBoat = {
    startDateTime: new Date(), 
    endDateTime: new Date(),
    id: 0,
    boat : this.boat
  }

  allFreeTerms : TermBoat[] = [];

  displayedColumns: string[] = ['startDateTime', 'endDateTime', 'price', 'customer', 'boat'];


  constructor(private _boatOwnerService: BoatOwnerService, private _snackBar: MatSnackBar,private _router : Router) { }

  ngOnInit(): void {
    this.boat = this._boatOwnerService.boat
    if(this.boat == null)
      this._router.navigateByUrl("boats");
    this.getAllReservationsForBoat();
    this.boatReservation.price = this.boat.price;
    this.specialOffer.price = this.boat.price
    this.getAllFreeTerms();
  }

  showUserInfo(boatReservation : BoatReservation)
  {
      this.boatReservation.customer = boatReservation.customer;
  }

  deleteService()
  {
    this.boat.additionalServices = this.boat.additionalServices.filter(item => !this.selectedServices.includes(item));
  }

  addNewService()
  {
    let newService  = Object.assign({},this.newService);
    this.boat.additionalServices.push(newService);
    this.newService.name = '';
    this.newService.price = 0;
  }
  editBoat()
  {
    this._boatOwnerService.editBoat(this.boat)
    .subscribe(data => {
      console.log('Dobio: ', data)
      if(data == null)
        this._snackBar.open('Incorrect filling of form! Check and send again edit request', 'Close', {duration: 5000});
      else
      {
        this.boat = data
      }
      },
      error => this.errorMessage = <any>error); 

    console.log(this.boat);
    this._snackBar.open('Successfully edited', 'Close', {duration: 5000});  
  }

  reserveBoat()
  {
    this.boatReservation.boat = this.boat
    this.boatReservation.endDateTime = this.getDateFromDatePickerRange(this.range.value.end)
    this.boatReservation.startDateTime = this.getDateFromDatePickerRange(this.range.value.start)
    this.boatReservation.endSpecialOffer = null
    this.boatReservation.price = this.boat.price
    let currentDate = new Date();
    if(this.boatReservation.endDateTime.getTime() < currentDate.getTime()){
        for (let service of this.boatReservation.services) {
          this.boatReservation.price += service.price
        }
        this._boatOwnerService.reserve(this.boatReservation)
              .subscribe(data => {
                if(data == null)
                  this._snackBar.open('Someone has reserved boat in selected term before you. Please select other term.', 'Close', {duration: 5000});
                else {
                  this.allReservationsForBoat = data,
                  this.allReservationsForBoat.filter(res => res.customer != null),
                  this._snackBar.open('Reservation successful', 'Close', {duration: 5000});
                }}
                ,error => this.errorMessage = <any>error); 
        this.boatReservation.customer = null;
        this.range.value.start = null;
        this.range.value.end = null;
        this.boatReservation.services = [];
        this.boatReservation.price = 0;
    }
    else
        this._snackBar.open('Reservation is not possible to make if current reservation has expired!', 'Close', {duration: 5000});   
  }

  makeSpecialOffer()
  {
    this.specialOffer.boat = this.boat;
    this.specialOffer.endDateTime = this.getDateFromDatePickerRange(this.rangeOffer.value.endOffer);
    this.specialOffer.startDateTime = this.getDateFromDatePickerRange(this.rangeOffer.value.startOffer);
    this.specialOffer.startSpecialOffer = this.getDateFromDatePickerRange(this.rangeDuration.value.startDuration);
    this.specialOffer.endSpecialOffer = this.getDateFromDatePickerRange(this.rangeDuration.value.endDuration);  
    for (let service of this.specialOffer.services) {
      this.specialOffer.price += service.price
    }
    this._boatOwnerService.makeSpecialOffer(this.specialOffer)
          .subscribe(data =>  {
            if(data == null)
              this._snackBar.open('Someone has reserved boat in selected term. Please select other term.', 'Close', {duration: 5000});
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
      this.boatReservation.price += selectedService.price
    else
      this.boatReservation.price -= selectedService.price
  }

  addFreeTerm()
  {
    this.newFreeTerm.startDateTime = this.rangeTerm.value.startTerm;
    this.newFreeTerm.endDateTime = this.rangeTerm.value.endTerm;
    this.newFreeTerm.boat = this.boat;
    if(this.newFreeTerm.startDateTime < this.newFreeTerm.endDateTime)
        if(this.newFreeTerm.startDateTime >= new Date()) //OVDE CE VEROVATNO TREBATI DODATI NEW FREE TERM I PRE SUBSCRIBA DA BI BILO ODMAH VIDLJIVO
          this._boatOwnerService.addFreeTerm(this.newFreeTerm)
          .subscribe(data =>  this.allFreeTerms = data,
            error => this.errorMessage = <any>error); 
    
   this._snackBar.open('Free term created successfuly', 'Close', {duration: 5000});
    this.rangeTerm.value.startTerm = null;
    this.rangeTerm.value.endTerm = null;          
  }

  getAllFreeTerms()
  {
       this._boatOwnerService.getAllFreeTermsForBoat(this.boat)
       .subscribe(data =>  this.allFreeTerms = data,
                  error => this.errorMessage = <any>error); 
  }

  getAllReservationsForBoat()
  {
    this._boatOwnerService.getAllReservationsForBoat(this.boat)
       .subscribe(data =>  {
                      this.allReservationsForBoat = data, 
                      this.allReservationsForBoat = this.allReservationsForBoat.filter(res => res.customer !== null)},
                  error => this.errorMessage = <any>error); 

  }

  removeBoat()
  {
    this._boatOwnerService.removeBoat(this.boat.id)
      .subscribe(data =>  { this.allFreeTerms = data,
                            this._router.navigateByUrl('boats');  
      },
                  error => this.errorMessage = <any>error); 

    
  }
}
