import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { subscribeTo } from 'rxjs/internal-compatibility';
import { Customer } from '../model/customer';
import { CustomerService } from '../service/customer.service';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent implements OnInit {

  customer!: Customer;
  oldCustomer!: Customer;
  errorMessage : string  = '';

  constructor(public _customerService: CustomerService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getCustomerByUsername();
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
                     console.log('Dobio: ', data)},
                   error => this.errorMessage = <any>error);    
  }



}

