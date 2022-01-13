import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { WeekendHouse } from '../model/weekend-house';
import { WeekendHouseOwner } from '../model/weekend-house-owner';
import { WeekendHouseReservation } from '../model/weekend-house-reservation';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';
import { WeekendHouseProfileComponent } from '../weekend-house-profile/weekend-house-profile.component';
import {MatSort, Sort} from '@angular/material/sort';
import { CustomerService } from '../service/customer.service';

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
  weekendHouses: WeekendHouse[] = []
  displayedColumns: string[] = ['name', 'address', 'description', 'bedNumber', 'owner', 'price'];
  errorMessage : string  = '';
  selectedHouseInfo: WeekendHouse = {
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
  }
  houseReservation : WeekendHouseReservation = {
    id: 0,
    startDateTime: new Date,
    endDateTime: new Date,
    peopleNumber: 0,
    startSpecialOffer: new Date,
    endSpecialOffer: new Date,
    services: [],
    price: 0,
    customer: {
      id: 0,
      firstName: "",
      lastName: "",
      email: "",
      username:  "",
      password: "",
      address: "",
      city: "",
      country: "",
      phone: ""
    },
    weekendHouse: this.selectedHouseInfo
  }
  role : string|null = localStorage.getItem('role');
  searchField: string = '';

  constructor(private _weekendHouseOwnerService: WeekendHouseOwnerService, private router: Router, private _snackBar: MatSnackBar, public _customerService: CustomerService) { }


  ngOnInit(): void {
    this.getAllWeekendHouses();
    this.getCustomer();
  }


  getAllWeekendHouses() {
    this._weekendHouseOwnerService.getAllWeekendHouses()
        .subscribe(data =>  {
                    this.weekendHouses = data
                    //this.dataSource = new MatTableDataSource(this.weekendHouses);
                    },
                   error => this.errorMessage = <any>error);   
  }

  getCustomer() {
    this._customerService.getCustomerByUsername(localStorage.getItem('username') || '')
              .subscribe(data => {
                this.houseReservation.customer = data
                console.log('Dobio: ', data)},
              error => this.errorMessage = <any>error);  
  }

  showInfo(house: WeekendHouse) {
    if(this.role == "ROLE_WEEKEND_HOUSE_OWNER")
    {
      this._weekendHouseOwnerService.weekendHouse = house;
      this.router.navigateByUrl("weekend-house-profile")
    }
    if(this.role != "ROLE_WEEKEND_HOUSE_OWNER")
    {
      this.selectedHouseInfo = house
      this.houseReservation.price = this.selectedHouseInfo.price
      this.houseReservation.peopleNumber = 1
    }    
  }

  findAvailableHousesForSelectedTerm() {
    this._weekendHouseOwnerService.findAvailableHousesForSelectedTerm(this.range.value.start, this.range.value.end)
          .subscribe(data =>  this.weekendHouses = data,
               error => this.errorMessage = <any>error); 
    this.selectedHouseInfo.id = 0;
  }

  reserve() {
    this.houseReservation.weekendHouse = this.selectedHouseInfo
    this.houseReservation.endDateTime = this.getDateFromDatePickerRange(this.range.value.end)
    this.houseReservation.startDateTime = this.getDateFromDatePickerRange(this.range.value.start)
    this.houseReservation.price = this.selectedHouseInfo.price
    for (let service of this.houseReservation.services) {
      this.houseReservation.price += service.price
    }
    this._weekendHouseOwnerService.reserve(this.houseReservation)
          .subscribe(data =>  this.weekendHouses = data,
               error => this.errorMessage = <any>error); 

    this.router.navigateByUrl('/').then(() => {
            this._snackBar.open('Reservation successful', 'Close', {duration: 5000});
            });               
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
          return compare(a.name, b.name, isAsc);
        case 'address':
          return compare(a.address, b.address, isAsc);
        case 'description':
          return compare(a.description, b.description, isAsc);
        case 'bedNumber':
          return compare(a.bedNumber, b.bedNumber, isAsc);
        case 'owner':
          return compare(a.weekendHouseOwner.firstName, b.weekendHouseOwner.firstName, isAsc);
        case 'price':
          return compare(a.price, b.price, isAsc);
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

    for (i = 0; i < tr.length; i++) {
      let allText = this.weekendHouses[i].name.toString() + ' ' + this.weekendHouses[i].address.toString() + ' ' + this.weekendHouses[i].description.toString() + ' ' + 
                      this.weekendHouses[i].bedNumber.toString() + ' ' + this.weekendHouses[i].price.toString() + ' ' + this.weekendHouses[i].weekendHouseOwner.firstName.toString() + ' ' +
                      this.weekendHouses[i].weekendHouseOwner.lastName.toString()
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
