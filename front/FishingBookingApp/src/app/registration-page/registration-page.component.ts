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
import { Router } from '@angular/router';

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
    phone: "",
    penals: 0,
    subscribedWeekendHouses: [],
      subscribedBoats: [],
      subscribedFishingLessons: [],
      category: '',
      discount: 0,
      points: 0,
      version: 0
  }
  errorMessage : string  = '';
  repassword: string = '';
  usernames: string[] = [];
  role: string = 'customer';
  nonCustomer : Instructor = {
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
  }


  constructor(public _customerService: CustomerService, public _boatOwnerService: BoatOwnerService,public _weekendHouseOwnerService: WeekendHouseOwnerService,
              public _instructorService: InstructorService, private _snackBar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.getAllUsernames();
  }

  submit(): void {
    if(this.role == "customer")
    {
      this.customer.penals = 1
      this._customerService.createCustomer(this.customer)
      .subscribe(
        data => {
          if(data == null)
            this._snackBar.open('User exists with same username.', 'Close', {duration: 5000});
          else {
            this.router.navigateByUrl('/').then(() => {
              this._snackBar.open('Registration request successfully submited! An activation mail has been sent to your inbox.', 'Close', {duration: 5000});
              }); 
          }
        },
        error => console.log('Error!', error)
      )
  
      
    }
    else{
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
        .subscribe(data => {
          this.usernames = data
          console.log(this.usernames);},
                    error => this.errorMessage = <any>error);
  }
}
