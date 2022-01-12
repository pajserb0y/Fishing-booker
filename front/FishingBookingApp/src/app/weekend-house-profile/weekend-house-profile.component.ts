import { Component, OnInit } from '@angular/core';
import { AdditionalService } from '../model/additional-service';
import { WeekendHouse } from '../model/weekend-house';
import { WeekendHouseReservation } from '../model/weekend-house-reservation';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';
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
  selectedServices : AdditionalService [] = [];
  displayedColumns: string[] = ['startDateTime', 'endDateTime', 'price', 'customer', 'weekendHouse'];

  constructor(private weekendHouseownerService: WeekendHouseOwnerService) { }

  ngOnInit(): void {
      this.weekendHouse = this.weekendHouseownerService.weekendHouse;
  }

  
  showUserInfo(weekendHouseReservation : WeekendHouseReservation)
  {

  }

  deleteService()
  {

  }

  addNewService(newService : AdditionalService)
  {

  }
  editWeekendHouse()
  {}

}
