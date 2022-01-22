import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { WeekendHouse } from '../model/weekend-house';
import { WeekendHouseReservation } from '../model/weekend-house-reservation';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';
import { WeekendHouseProfileComponent } from '../weekend-house-profile/weekend-house-profile.component';
import {MatSort, Sort} from '@angular/material/sort';
import { CustomerService } from '../service/customer.service';
import { WeekendHouseWithAvgGrade } from '../model/weekend-house-with-avg-grade';
import { Customer } from '../model/customer';

@Component({
  selector: 'app-weekend-houses',
  templateUrl: './weekend-houses.component.html',
  styleUrls: ['./weekend-houses.component.css']
})
export class WeekendHousesComponent implements OnInit {

  minDate = new Date();
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });
  weekendHouses: WeekendHouseWithAvgGrade[] = []
  displayedColumns: string[] = ['name', 'address', 'description', 'bedNumber', 'owner', 'price', 'grade'];
  errorMessage : string  = '';
  selectedHouseInfo: WeekendHouse = {
    id: 0,
    name: '' ,
    address: '' ,
    description: '' ,
        /* grade: number ;   ovo se dobavlja iz tabele svih ocena*/
    imagePath : [] ,
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
  }
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
    weekendHouse: this.selectedHouseInfo,
    cancelled: false
  }
  role : string|null = localStorage.getItem('role');
  searchField: string = '';
  show: boolean = false;
  currentCustomer! : Customer;
  

  constructor(private _weekendHouseOwnerService: WeekendHouseOwnerService, private router: Router, private _snackBar: MatSnackBar, public _customerService: CustomerService) { }
  username: string|null = localStorage.getItem('username');


  ngOnInit(): void {
    if(this.role == 'ROLE_CUSTOMER')
    {
      this.getCustomer();
      this.getAllWeekendHouses();
    }    
    else if(this.role == 'ROLE_WEEKEND_HOUSE_OWNER')
        this.getAllWeekendHousesForOwner(this.username)   
    else
      this.getAllWeekendHouses();
  }


  getAllWeekendHouses() {
    this._weekendHouseOwnerService.getAllWeekendHouses()
        .subscribe(data =>  {
                    this.weekendHouses = data
                    //this.dataSource = new MatTableDataSource(this.weekendHouses);
                    },
                   error => this.errorMessage = <any>error);   
  }

  getAllWeekendHousesForOwner(username :String|null)
  {
    this._weekendHouseOwnerService.getAllWeekendHousesForOwner(username)
        .subscribe(data =>  this.weekendHouses = data,
                   error => this.errorMessage = <any>error); 
  }

  getAllFreeTerms() {
       this._weekendHouseOwnerService.getAllFreeTermsForWeekendHouse(this.selectedHouseInfo)
       .subscribe(data =>  this.selectedHouseInfo.freeTerms = data,
                  error => this.errorMessage = <any>error); 
  }

  getCustomer() {
    this._customerService.getCustomerByUsername(localStorage.getItem('username') || '')
              .subscribe(data => {
                this.houseReservation.customer = data
                this.currentCustomer = data
                console.log('Dobio: ', data)},
              error => this.errorMessage = <any>error);  
  }

  showInfo(house: WeekendHouse) {
    if(this.role == 'ROLE_WEEKEND_HOUSE_OWNER')
    {
      this._weekendHouseOwnerService.weekendHouse = house;
      this.router.navigateByUrl("weekend-house-profile")
    }
    if(this.role != 'ROLE_WEEKEND_HOUSE_OWNER')
    {
      this.selectedHouseInfo = house
      this.houseReservation.price = this.selectedHouseInfo.price * (100 - this.currentCustomer.discount)/100
      this.houseReservation.peopleNumber = 1
      if(this.role == 'ROLE_CUSTOMER')
        this.getAllFreeTerms();
    }    
    this.show = true;
  }

  subscribe() {
    var exists = false;
    for (var house of this.currentCustomer.subscribedWeekendHouses)
      if(house.id == this.selectedHouseInfo.id) {
        exists = true;
        break;
      }
    if(!exists) {
      this._customerService.subscribeCustomerForWeekendHouse(localStorage.getItem('username') || '', this.selectedHouseInfo.id)
                .subscribe(data =>  {
                  this._snackBar.open('You are succesfully subscribed for this house', 'Close', {duration: 5000})
                    window.location.reload();
                  },
                    error => this.errorMessage = <any>error); 
    } else 
        this._snackBar.open('You are already subscribed for this house', 'Close', {duration: 5000});
  }

  findAvailableHousesForSelectedTerm() {
    this._weekendHouseOwnerService.findAvailableHousesForSelectedTerm(this.range.value.start, this.range.value.end)
          .subscribe(data =>  this.weekendHouses = data,
               error => this.errorMessage = <any>error); 
    this.show = false;
  }

  reserve() {
    if(this.houseReservation.customer != null && this.houseReservation.customer.penals > 2)
        this._snackBar.open('You can not make any reservations because you are banned for the end of the month!', 'Close', {duration: 5000});
    else {
        this.houseReservation.weekendHouse = this.selectedHouseInfo
        this.houseReservation.endDateTime = this.getDateFromDatePickerRange(this.range.value.end)
        this.houseReservation.startDateTime = this.getDateFromDatePickerRange(this.range.value.start)
        this.houseReservation.endSpecialOffer = null
        this.houseReservation.price = this.selectedHouseInfo.price * (100 - this.currentCustomer.discount)/100
        for (let service of this.houseReservation.services) {
          this.houseReservation.price += service.price * (100 - this.currentCustomer.discount)/100
        }
        this._weekendHouseOwnerService.reserve(this.houseReservation)
              .subscribe(data => {
                if(data == null)
                  this._snackBar.open('Someone has reserved house in selected term before you. Please select other term.', 'Close', {duration: 5000});
                else {
                  this.router.navigateByUrl('customer-future-weekend-house-reservations').then(() => {
                  this._snackBar.open('Reservation successful', 'Close', {duration: 5000});
                  }); 
                }  
              },
                  error => this.errorMessage = <any>error);   
    }            
  }

  calculateTotalPrice(ob: any) {
    let selectedService = ob.source.value;
    console.log(selectedService);
    if (ob.source._selected)
      this.houseReservation.price += selectedService.price * (100 - this.currentCustomer.discount)/100
    else
      this.houseReservation.price -= selectedService.price * (100 - this.currentCustomer.discount)/100
  }

  getDateFromDatePickerRange(start: Date) {
    return new Date(Date.UTC(start.getFullYear(), start.getMonth(), start.getDate(), start.getHours(), start.getMinutes()));
  }

  sortData(sort: Sort) {
    const data = this.weekendHouses.slice();
    if (!sort.active || sort.direction === '') {
      this.weekendHouses = data;
      return;
    }

    this.weekendHouses = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name':
          return compare(a.weekendHouse.name, b.weekendHouse.name, isAsc);
        case 'address':
          return compare(a.weekendHouse.address, b.weekendHouse.address, isAsc);
        case 'description':
          return compare(a.weekendHouse.description, b.weekendHouse.description, isAsc);
        case 'bedNumber':
          return compare(a.weekendHouse.bedNumber, b.weekendHouse.bedNumber, isAsc);
        case 'owner':
          return compare(a.weekendHouse.weekendHouseOwner.firstName, b.weekendHouse.weekendHouseOwner.firstName, isAsc);
        case 'price':
          return compare(a.weekendHouse.price, b.weekendHouse.price, isAsc);
        case 'grade':
          return compare(a.avgGrade, b.avgGrade, isAsc);
        default:
          return 0;
      }
    });
  }

  searchTable() {
    var table, tr, i;

    table = <HTMLTableElement>document.getElementById("myTable");
    tr = table.getElementsByTagName("mat-row") as HTMLCollectionOf<HTMLElement>;        

    var hideList = [];
    var grade;

    for (i = 0; i < tr.length; i++) {
      if (this.weekendHouses[i].avgGrade == null)
        grade = ''
      else
        grade =  this.weekendHouses[i].avgGrade.toString()
      let allText = this.weekendHouses[i].weekendHouse.name.toString() + ' ' + this.weekendHouses[i].weekendHouse.address.toString() + ' ' + this.weekendHouses[i].weekendHouse.description.toString() + ' ' + 
                      this.weekendHouses[i].weekendHouse.bedNumber.toString() + ' ' + this.weekendHouses[i].weekendHouse.price.toString() + ' ' + this.weekendHouses[i].weekendHouse.weekendHouseOwner.firstName.toString() + ' ' +
                      this.weekendHouses[i].weekendHouse.weekendHouseOwner.lastName.toString() + ' ' + grade
      if (!(allText.toUpperCase().indexOf(this.searchField.toUpperCase()) > -1)) 
                hideList.push(i);
    }    
    
    for (i = 0; i < tr.length; i++)
    {
        if (hideList.includes(i))
            tr[i].style.display = "none";
        else 
            tr[i].style.display = "";
    }
    hideList.length = 0;  //clear the map
  }

}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
