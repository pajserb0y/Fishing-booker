import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { subscribeTo } from 'rxjs/internal-compatibility';
import { Customer } from '../model/customer';
import { DeleteDto } from '../model/deleteDto';
import { CustomerService } from '../service/customer.service';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent implements OnInit {

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
  oldCustomer!: Customer;
  errorMessage : string  = '';
  deleteDto : DeleteDto = {
    id: 0,
    note: "",
  };

  constructor(public _customerService: CustomerService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getCustomerByUsername();
    //this.deleteDto.id = this.customer.id;
  }


  edit() {
    this._customerService.edit(this.customer)
          .subscribe(data => {
            this.customer = data
            console.log('Dobio: ', data)},
          error => this.errorMessage = <any>error); 

    console.log(this.customer);
    this._snackBar.open('Successfully edited', 'Close', {duration: 5000});
  }

  getCustomerByUsername() {
    this._customerService.getCustomerByUsername(localStorage.getItem('username') || '')
         .subscribe(data => {
                     this.customer = data
                     this.oldCustomer = data
                     this.deleteDto.id = this.customer.id
                     console.log('Dobio: ', data)},
                   error => this.errorMessage = <any>error);    
  }

  delete() {
    this._customerService.sendDelete(this.deleteDto)
            .subscribe(data => {
              console.log('Dobio: ', data)},
            error => this.errorMessage = <any>error); 

    this.deleteDto.note = '';
    this._snackBar.open('Your request has been successfuly sent. Please wait while we proccess your request', 'Close', {duration: 5000});
  }

}

