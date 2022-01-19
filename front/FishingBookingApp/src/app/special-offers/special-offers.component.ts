import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { BoatReservation } from '../model/boat-reservation';
import { BoatReservationWithDateAsString } from '../model/boat-reservation-with-date-as-string';
import { Customer } from '../model/customer';
import { FishingLessonReservation } from '../model/fishing-lesson-reservation';
import { FishingLessonReservationWithDateAsString } from '../model/fishing-lesson-reservation-with-date-as-string';
import { SpecialOffer } from '../model/special-offer';
import { WeekendHouseReservation } from '../model/weekend-house-reservation';
import { WeekendHouseReservationWithDateAsString } from '../model/weekend-house-reservation-with-date-as-string';
import { BoatOwnerService } from '../service/boat-owner.service';
import { CustomerService } from '../service/customer.service';
import { InstructorService } from '../service/instructor.service';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';

@Component({
  selector: 'app-special-offers',
  templateUrl: './special-offers.component.html',
  styleUrls: ['./special-offers.component.css']
})
export class SpecialOffersComponent implements OnInit {

  customer! : Customer;
  specialOffers : SpecialOffer[] = [];
  displayedColumns: string[] = ['entity', 'name', 'startDate', 'endDate', 'originalPrice', 'discountPrice', 'reserveNow'];
  errorMessage : string  = '';
  pipe = new DatePipe('en-US'); // Use your own locale
  fishingLessonReservations : FishingLessonReservationWithDateAsString[] = [];
  boatReservations : BoatReservationWithDateAsString[] = []
  houseReservations : WeekendHouseReservationWithDateAsString[] = []

  houseReservation : WeekendHouseReservation = {
    id: 0,
    startDateTime: new Date,
    endDateTime: new Date,
    peopleNumber: 0,
    startSpecialOffer: new Date,
    endSpecialOffer: null,
    services: [],
    price: 0,
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
    weekendHouse: {
      id: 0,
      name: '' ,
      address: '' ,
      description: '' ,
          /* grade: number ;   ovo se dobavlja iz tabele svih ocena*/
      imagePath : '' ,
      bedNumber: 0,
      freeTerms: [],
      rules: '' ,
      price: 0 ,
      additionalServices: [],
      weekendHouseOwner: {
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
        motive: ""
      },
      weekendHouseReservations: [],
    },
    cancelled: false
  }
  boatReservation : BoatReservation = {
    id: 0,
    startDateTime: new Date,
    endDateTime: new Date,
    capacity: 0,
    startSpecialOffer: new Date,
    endSpecialOffer: null,
    services: [],
    price: 0,
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
    boat: {
      id: 0,
      name: '' ,
      address: '' ,
      description: '' ,
          /* grade: number ;   ovo se dobavlja iz tabele svih ocena*/
      imagePath : '' ,
      capacity: 0,
      freeTerms: [],
      rules: '' ,
      price: 0 ,
      additionalServices: [],
      boatOwner: {
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
        motive: ""
      },
      boatReservations: [],
      avgGrade: 0
    },
    cancelled: false
  }
  fishingReservation : FishingLessonReservation = {
    id: 0,
    startDateTime: new Date,
    endDateTime: new Date,
    maxPeopleNumber: 0,
    startSpecialOffer: new Date,
    endSpecialOffer: null,
    additionalServices: [],
    price: 0,
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
    fishingLesson: {
      id: 0,
      name: '',
      address: '',
      description: '',
      imagePaths: '',
      maxNumberOfPeople: 0,
      freeTerms: [],
      rules: '',
      price: 0,
      additionalServices: [],
      instructor: {
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
      fishingReservations: [],
      avgGrade: 0,
      fishingEquipment: '',
      cancelConditions: ''
    },
    cancelled: false
  }


  constructor(private _instructorService: InstructorService, private _boatOwnerService: BoatOwnerService, private _weekendHouseOwnerService: WeekendHouseOwnerService,
              private _snackBar: MatSnackBar, private router: Router, public _customerService: CustomerService) { }

  ngOnInit(): void {
    this.specialOffers = [];
    this.getCustomer();
    this.getSpecialOffers();
  }


  getCustomer() {
    this._customerService.getCustomerByUsername(localStorage.getItem('username') || '')
              .subscribe(data => {
                this.customer = data
                console.log('Dobio: ', data)},
              error => this.errorMessage = <any>error);  
  }

  getSpecialOffers() {
    this._instructorService.getCurrentSpecialOffers()
        .subscribe(data =>  {
                    this.fishingLessonReservations = data   
                    var temp = this.specialOffers         
                    this.specialOffers = []               
                      for (let res of this.fishingLessonReservations) {
                        res.startDateTime = this.pipe.transform(new Date(res.startDateTime), 'medium') || ''
                        res.endDateTime = this.pipe.transform(new Date(res.endDateTime), 'medium') || ''                        
                        
                          this.specialOffers.push({
                            id: res.id,
                            startDateTime: res.startDateTime,
                            endDateTime: res.endDateTime,
                            peopleNumber: res.maxPeopleNumber,
                            startSpecialOffer: res.startSpecialOffer,
                            endSpecialOffer: res.endSpecialOffer,
                            services: res.additionalServices,
                            originalPrice: res.price,
                            discountPrice: res.price * 0.8 * (100 - this.customer.discount)/100,
                            customer: res.customer,
                            entity: res.fishingLesson,
                            entityType: 'Fishing lesson',
                            cancelled: res.cancelled,
                          })
                        }
                        this.specialOffers = this.specialOffers.concat(temp)                     
                    this._boatOwnerService.getCurrentSpecialOffers()
                          .subscribe(data =>  {
                                      this.boatReservations = data     
                                      var temp = this.specialOffers         
                                      this.specialOffers = []                 
                                      for (let res of this.boatReservations) {
                                        res.startDateTime = this.pipe.transform(new Date(res.startDateTime), 'medium') || ''
                                        res.endDateTime = this.pipe.transform(new Date(res.endDateTime), 'medium') || ''

                                          this.specialOffers.push({
                                            id: res.id,
                                            startDateTime: res.startDateTime,
                                            endDateTime: res.endDateTime,
                                            peopleNumber: res.capacity,
                                            startSpecialOffer: res.startSpecialOffer,
                                            endSpecialOffer: res.endSpecialOffer,
                                            services: res.services,
                                            originalPrice: res.price,
                                            discountPrice: res.price * 0.8 * (100 - this.customer.discount)/100,
                                            customer: res.customer,
                                            entity: res.boat,
                                            entityType: 'Boat',
                                            cancelled: res.cancelled,
                                          })
                                      }
                                      this.specialOffers = this.specialOffers.concat(temp) 
                                      this._weekendHouseOwnerService.getCurrentSpecialOffers()
                                              .subscribe(data =>  {
                                                          this.houseReservations = data    
                                                          var temp = this.specialOffers         
                                                          this.specialOffers = []       
                                                          for (let res of this.houseReservations) {
                                                            res.startDateTime = this.pipe.transform(new Date(res.startDateTime), 'medium') || ''
                                                            res.endDateTime = this.pipe.transform(new Date(res.endDateTime), 'medium') || ''
                                                                                                                       
                                                              temp.push({
                                                                id: res.id,
                                                                startDateTime: res.startDateTime,
                                                                endDateTime: res.endDateTime,
                                                                peopleNumber: res.peopleNumber,
                                                                startSpecialOffer: res.startSpecialOffer,
                                                                endSpecialOffer: res.endSpecialOffer,
                                                                services: res.services,
                                                                originalPrice: res.price,
                                                                discountPrice: res.price * 0.8 * (100 - this.customer.discount)/100,
                                                                customer: res.customer,
                                                                entity: res.weekendHouse,
                                                                entityType: 'Weekend house',
                                                                cancelled: res.cancelled,
                                                              })                                                          
                                                          }      
                                                          this.specialOffers = this.specialOffers.concat(temp)                                         
                                                          },
                                                        error => this.errorMessage = <any>error);
                                      },
                                    error => this.errorMessage = <any>error);
                    },
                   error => this.errorMessage = <any>error);
    
  }

  reserveNow(specialOffer : SpecialOffer) {
    if(specialOffer.entityType == 'Weekend house') {
        this.houseReservation.id = specialOffer.id
        this.houseReservation.weekendHouse = specialOffer.entity
        this.houseReservation.endDateTime = new Date(specialOffer.endDateTime)
        this.houseReservation.startDateTime = new Date(specialOffer.startDateTime)
        this.houseReservation.endSpecialOffer = new Date(specialOffer.endSpecialOffer)
        this.houseReservation.startSpecialOffer = new Date(specialOffer.startSpecialOffer)
        this.houseReservation.price = specialOffer.discountPrice
        this.houseReservation.peopleNumber = specialOffer.peopleNumber
        this.houseReservation.cancelled = specialOffer.cancelled
        this.houseReservation.customer = this.customer

        this._weekendHouseOwnerService.reserve(this.houseReservation)
              .subscribe(data => {
                if(data == null)
                  this._snackBar.open('Someone has reserved house in selected term before you. Please select other term.', 'Close', {duration: 5000});
                else {
                  this.router.navigateByUrl('customer-future-weekend-house-reservations').then(() => {
                    this._snackBar.open('Reservation successful', 'Close', {duration: 5000});
                    window.location.reload(); //pomeri iz tabele tu speical offer
                  }); 
                }
              },error => this.errorMessage = <any>error); 
    }    
    else if(specialOffer.entityType == 'Boat') {
      this.boatReservation.id = specialOffer.id
      this.boatReservation.boat = specialOffer.entity
      this.boatReservation.endDateTime = new Date(specialOffer.endDateTime)
      this.boatReservation.startDateTime = new Date(specialOffer.startDateTime)
      this.boatReservation.endSpecialOffer = new Date(specialOffer.endSpecialOffer)
      this.boatReservation.startSpecialOffer = new Date(specialOffer.startSpecialOffer)
      this.boatReservation.price = specialOffer.discountPrice
      this.boatReservation.capacity = specialOffer.peopleNumber
      this.boatReservation.cancelled = specialOffer.cancelled
      this.boatReservation.customer = this.customer

      this._boatOwnerService.reserve(this.boatReservation)
            .subscribe(data => {if(data == null)
              this._snackBar.open('Someone has reserved boat in selected term before you. Please select other term.', 'Close', {duration: 5000});
            else {
              this.router.navigateByUrl('customer-future-boat-reservations').then(() => {
                this._snackBar.open('Reservation successful', 'Close', {duration: 5000});
                window.location.reload(); //pomeri iz tabele tu speical offer
              }); 
            }
          },error => this.errorMessage = <any>error);
    }
    else if(specialOffer.entityType == 'Fishing lesson') {
      this.fishingReservation.id = specialOffer.id
      this.fishingReservation.fishingLesson = specialOffer.entity
      this.fishingReservation.endDateTime = new Date(specialOffer.endDateTime)
      this.fishingReservation.startDateTime = new Date(specialOffer.startDateTime)
      this.fishingReservation.endSpecialOffer = new Date(specialOffer.endSpecialOffer)
      this.fishingReservation.startSpecialOffer = new Date(specialOffer.startSpecialOffer)
      this.fishingReservation.price = specialOffer.discountPrice
      this.fishingReservation.maxPeopleNumber = specialOffer.peopleNumber
      this.fishingReservation.cancelled = specialOffer.cancelled
      this.fishingReservation.customer = this.customer

      this._instructorService.reserve(this.fishingReservation)
            .subscribe(data => {if(data == null)
              this._snackBar.open('Someone has reserved fishing lesson in selected term before you. Please select other term.', 'Close', {duration: 5000});
            else {
              this.router.navigateByUrl('customer-future-fishing-lesson-reservations').then(() => {
                this._snackBar.open('Reservation successful', 'Close', {duration: 5000});
                window.location.reload(); //pomeri iz tabele tu speical offer
              }); 
            }
          },error => this.errorMessage = <any>error); 
    }  
  }

  
  sortData(sort: Sort) {
    const data = this.specialOffers.slice();
    if (!sort.active || sort.direction === '') {
      this.specialOffers = data;
      return;
    }

    this.specialOffers = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name':
          return compare(a.entity.name, b.entity.name, isAsc);
        case 'startDate':
          return compare(new Date(a.startDateTime), new Date(b.startDateTime), isAsc);
        case 'endDate':
          return compare(new Date(a.endDateTime), new Date(b.endDateTime), isAsc);
        case 'entity':
          return compare(a.entityType, b.entityType, isAsc);
        case 'peopleNumber':
          return compare(a.peopleNumber, b.peopleNumber, isAsc);
        case 'originalPrice':
          return compare(a.originalPrice, b.originalPrice, isAsc);
        case 'discountPrice':
          return compare(a.discountPrice, b.discountPrice, isAsc);
        default:
          return 0;
      }
    });
  }
}

function compare(a: number | string | Date, b: number | string | Date, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }


