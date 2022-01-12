import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { WeekendHouse } from '../model/weekend-house';
import { WeekendHouseOwner } from '../model/weekend-house-owner';
import { WeekendHouseReservation } from '../model/weekend-house-reservation';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';

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

  constructor(private _weekendHouseOwnerService: WeekendHouseOwnerService, private router: Router) { }

  ngOnInit(): void {
    this.getAllWeekendHouses();
  }


  getAllWeekendHouses() {
    this._weekendHouseOwnerService.getAllWeekendHouses()
        .subscribe(data =>  this.weekendHouses = data,
                   error => this.errorMessage = <any>error);   
  }

  showInfo(house: WeekendHouse) {

  }

  reserve() {
    
  }

}
