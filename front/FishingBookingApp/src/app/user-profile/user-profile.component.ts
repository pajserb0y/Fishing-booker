import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { subscribeTo } from 'rxjs/internal-compatibility';
import { Customer } from '../model/customer';
import { DeleteDto } from '../model/deleteDto';
import { Instructor } from '../model/instructor';
import { BoatOwnerService } from '../service/boat-owner.service';
import { CustomerService } from '../service/customer.service';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  customer: Customer = {
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
  };
  repassword: string = "";
  nonCustomer!: Instructor;
  oldCustomer!: Customer;
  errorMessage : string  = '';
  deleteDto : DeleteDto = {
    id: 0,
    note: "",
  };
  role: string|null = localStorage.getItem('role');

  constructor(public _customerService: CustomerService,private _boatOwnerService: BoatOwnerService,private _weekendHouseService: WeekendHouseOwnerService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getUserByUsername();
    //this.deleteDto.id = this.customer.id;
  }


  edit() {
    if(this.role == 'ROLE_CUSTOMER') 
    {
      this._customerService.edit(this.customer)
          .subscribe(data => {
            console.log('Dobio: ', data)
            if(data == null)
              this._snackBar.open('Incorrect filling of form! Check and send again edit request', 'Close', {duration: 5000});
            else
              this.customer = data
            },
          error => this.errorMessage = <any>error); 

      console.log(this.customer);
      this._snackBar.open('Successfully edited', 'Close', {duration: 5000}); 
    }
    else if(this.role == 'ROLE_ADMIN')
    {

    }
    else if(this.role == 'ROLE_BOAT_OWNER')
    {
      //this.customerToNonCustomer();
      this._boatOwnerService.edit(this.nonCustomer)
          .subscribe(data => {
            console.log('Dobio: ', data)
            if(data == null)
              this._snackBar.open('Incorrect filling of form! Check and send again edit request', 'Close', {duration: 5000});
            else
            {
              this.nonCustomer = data;
              this.nonCustomerToCustomer();//sad su u customeru sva bitna polja za prikaz od nonCustomera
            }
            },
          error => this.errorMessage = <any>error); 

      console.log(this.customer);
      this._snackBar.open('Successfully edited', 'Close', {duration: 5000});   
    }
    else if(this.role == 'ROLE_WEEKEND_HOUSE_OWNER')
    {
      //this.customerToNonCustomer();
      this._weekendHouseService.edit(this.nonCustomer)
          .subscribe(data => {
            console.log('Dobio: ', data)
            if(data == null)
              this._snackBar.open('Incorrect filling of form! Check and send again edit request', 'Close', {duration: 5000});
            else
            {
              this.nonCustomer = data;
              this.nonCustomerToCustomer();//sad su u customeru sva bitna polja za prikaz od nonCustomera
            }
            },
          error => this.errorMessage = <any>error); 

      console.log(this.customer);
      this._snackBar.open('Successfully edited', 'Close', {duration: 5000});   
    }
    else if(this.role == 'ROLE_INSTRUCTOR')
    {
      
    }  
  }


  getUserByUsername()
  {
    if(this.role == 'ROLE_CUSTOMER') 
    {
      this._customerService.getCustomerByUsername(localStorage.getItem('username') || '')
      .subscribe(data => {
                  this.customer = data
                  this.repassword = this.customer.password;
                  this.oldCustomer = data
                  this.deleteDto.id = this.customer.id
                  console.log('Dobio: ', data)},
                error => this.errorMessage = <any>error);  
    }
    else if(this.role == 'ROLE_ADMIN')
    {

    }
    else if(this.role == 'ROLE_BOAT_OWNER')
    {
      this._boatOwnerService.getBoatOwnerByUsername(localStorage.getItem('username') || '')
      .subscribe(data => {
                  this.nonCustomer = data
                  this.nonCustomerToCustomer()//porebaci nonCustomer-a u customera zbog lakseg prikaza
                  this.repassword = this.customer.password;
                  this.oldCustomer = data
                  this.deleteDto.id = this.customer.id
                  console.log('Dobio: ', data)},
                error => this.errorMessage = <any>error);   
    }
    else if(this.role == 'ROLE_WEEKEND_HOUSE_OWNER')
    {
      this._weekendHouseService.getWeekendHouseOwnerByUsername(localStorage.getItem('username') || '')
      .subscribe(data => {
                  this.nonCustomer = data
                  this.nonCustomerToCustomer()//porebaci nonCustomer-a u customera zbog lakseg prikaza
                  this.repassword = this.customer.password;
                  this.oldCustomer = data
                  this.deleteDto.id = this.customer.id
                  console.log('Dobio: ', data)},
                error => this.errorMessage = <any>error);
    }
    else if(this.role == 'ROLE_INSTRUCTOR')
    {
      
    }  
  }

  delete() {
    this._customerService.sendDelete(this.deleteDto)
            .subscribe(data => {
              console.log('Dobio: ', data)},
            error => this.errorMessage = <any>error); 

    this.deleteDto.note = '';
    this._snackBar.open('Your request has been successfuly sent. Please wait while we proccess your request', 'Close', {duration: 5000});
  }

  nonCustomerToCustomer()
  {
    this.customer.id = this.nonCustomer.id;
    this.customer.firstName= this.nonCustomer.firstName;
    this.customer.lastName= this.nonCustomer.lastName;
    this.customer.email= this.nonCustomer.email;
    this.customer.username= this.nonCustomer.username;
    this.customer.password= this.nonCustomer.password;
    this.customer.address= this.nonCustomer.address;
    this.customer.city= this.nonCustomer.city;
    this.customer.country= this.nonCustomer.country;
    this.customer.phone= this.nonCustomer.phone;
  }

/*customerToNonCustomer()
{
  this.nonCustomer.id = this.customer.id;
  this.nonCustomer.firstName= this.customer.firstName;
  this.nonCustomer.lastName= this.customer.lastName;
  this.nonCustomer.email= this.customer.email;
  this.nonCustomer.username= this.customer.username;
  this.nonCustomer.password= this.customer.password;
  this.nonCustomer.address= this.customer.address;
  this.nonCustomer.city= this.customer.city;
  this.nonCustomer.country= this.customer.country;
  this.nonCustomer.phone= this.customer.phone;
  this.nonCustomer.motive = "";
}*/
}

