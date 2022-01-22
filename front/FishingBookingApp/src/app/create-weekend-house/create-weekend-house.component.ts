import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdditionalService } from '../model/additional-service';
import { WeekendHouse } from '../model/weekend-house';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';

@Component({
  selector: 'app-create-weekend-house',
  templateUrl: './create-weekend-house.component.html',
  styleUrls: ['./create-weekend-house.component.css']
})
export class CreateWeekendHouseComponent implements OnInit {

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
  username: string|null = localStorage.getItem('username');
  pictures: any = [];
  selectedFiles : string[] = []
  image : any;

  constructor(private _weekendHouseownerService: WeekendHouseOwnerService, private _snackBar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    if (this.username != null)
      this._weekendHouseownerService.getWeekendHouseOwnerByUsername(this.username)
          .subscribe(data => this.weekendHouse.weekendHouseOwner = data,
                     error => this.errorMessage = <any>error)
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

  createWeekendHouse()
  {
    this.weekendHouse.imagePath = this.pictures
    this._weekendHouseownerService.createWeekendHouse(this.weekendHouse)
      .subscribe(data => {
        console.log('Dobio: ', data)
        if(data == null)
          this._snackBar.open('Incorrect filling of form! Check and send again create request', 'Close', {duration: 5000});
        else
          this.weekendHouse = data     
        },
        error => this.errorMessage = <any>error); 

    console.log(this.weekendHouse);
    this._snackBar.open('Successfully create Weekend House', 'Close', {duration: 5000});  
  }

  onFileSelected(event : any) {
    if(!event || !event.target || !event.target.files) {
      return;
    }

    var file = event.target.files[0]
    var reader = new FileReader();
    var self = this
    reader.readAsDataURL(file);
    reader.onload = function () {
      self.pictures.push(reader.result)
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
    };

    console.log(this.pictures)
  }

}
