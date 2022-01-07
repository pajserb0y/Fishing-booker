import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Customer } from '../model/customer';
import { CustomerService } from '../service/customer.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {

  emailControl: FormControl = new FormControl('', [Validators.required, Validators.email]);
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
  }
  errorMessage : string  = '';
  repassword: string = '';

  constructor(public _customerService: CustomerService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  submit(): void {

    this._customerService.createCustomer(this.customer)
    .subscribe(
      data => console.log('Success!', data),
      error => console.log('Error!', error)
    )

    console.log(this.customer);
    this._snackBar.open('Registration request successfully submited! An activation mail has been sent to your inbox.', 'Close', {duration: 5000});
  }

}
