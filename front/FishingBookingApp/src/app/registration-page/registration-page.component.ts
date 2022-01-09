import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { Customer } from '../model/customer';
import { CustomerService } from '../service/customer.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { Instructor } from '../model/instructor';
import { BoatOwnerService } from '../service/boat-owner.service';
import { WeekendHouseOwnerService } from '../service/weekend-house-owner.service';
import { InstructorService } from '../service/instructor.service';

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}


@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {

  emailControl: FormControl = new FormControl('', [Validators.required, Validators.email]);
  matcher = new MyErrorStateMatcher();
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
  usernames: Array<string> = [];
  motive: string = '';
  role: string = 'customer';
  nonCustomer !: Instructor;


  constructor(public _customerService: CustomerService, public _boatOwnerService: BoatOwnerService,public _weekendHouseOwnerService: WeekendHouseOwnerService,public _instructorService: InstructorService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getAllUsernames();
  }

  submit(): void {
    if(this.role == "customer")
    {
      this._customerService.createCustomer(this.customer)
      .subscribe(
        data => console.log('Success!', data),
        error => console.log('Error!', error)
      )
  
      console.log(this.customer);
      this._snackBar.open('Registration request successfully submited! An activation mail has been sent to your inbox.', 'Close', {duration: 5000});
    }
    else{
      if(this.role == "boatOwner")
      {
        this._boatOwnerService.createBoatOwner(this.nonCustomer)
        .subscribe(
          data => console.log('Success!', data),
          error => console.log('Error!', error)
        )
    
        console.log(this.nonCustomer);
        this._snackBar.open('Registration request successfully submited! An admin has been notified.', 'Close', {duration: 5000});
      }
      else if(this.role == "weekendHouseOwner")
      {
        this._weekendHouseOwnerService.createWeekendHouseOwner(this.nonCustomer)
        .subscribe(
          data => console.log('Success!', data),
          error => console.log('Error!', error)
        )
    
        console.log(this.nonCustomer);
        this._snackBar.open('Registration request successfully submited! An admin has been notified.', 'Close', {duration: 5000});
      }
      else if(this.role == "instructor")
      {
        this._instructorService.createInstructor(this.nonCustomer)
        .subscribe(
          data => console.log('Success!', data),
          error => console.log('Error!', error)
        )
    
        console.log(this.nonCustomer);
        this._snackBar.open('Registration request successfully submited! An admin has been notified.', 'Close', {duration: 5000});
      }
    }
   

  }

  getAllUsernames() {
    this._customerService.getAllUsernames()
        .subscribe(data => this.usernames = data,
                    error => this.errorMessage = <any>error);
  }

}
