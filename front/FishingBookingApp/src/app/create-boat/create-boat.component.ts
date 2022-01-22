import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdditionalService } from '../model/additional-service';
import { Boat } from '../model/boat';
import { BoatReservation } from '../model/boat-reservation';
import { BoatOwnerService } from '../service/boat-owner.service';

@Component({
  selector: 'app-create-boat',
  templateUrl: './create-boat.component.html',
  styleUrls: ['./create-boat.component.css']
})
export class CreateBoatComponent implements OnInit {

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
    imagePath: [],
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
  username: string|null = localStorage.getItem('username');
  pictures: any = [];
  selectedFiles : string[] = []
  image : any;

  constructor(private _boatOwnerService: BoatOwnerService, private _snackBar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    if (this.username != null)
      this._boatOwnerService.getBoatOwnerByUsername(this.username)
          .subscribe(data => this.boat.boatOwner = data,
                     error => this.errorMessage = <any>error)
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

  createBoat()
  {
    this.boat.imagePath = this.pictures
    this._boatOwnerService.createBoat(this.boat)
      .subscribe(data => {
        console.log('Dobio: ', data)
        if(data == null)
          this._snackBar.open('Incorrect filling of form! Check and send again create request', 'Close', {duration: 5000});
        else
          this.boat = data     
        },
        error => this.errorMessage = <any>error); 

    console.log(this.boat);
    this._snackBar.open('Successfully created Boat', 'Close', {duration: 5000});  
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
