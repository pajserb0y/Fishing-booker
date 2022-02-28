import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Credentials } from '../model/credentials';
import { CustomerService } from '../service/customer.service';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  credentials: Credentials = {
    username: '',
    password: ''
  }

  token: string = '';

  constructor(public _customerService: CustomerService, private router: Router, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }


  submit(): void {
    this._customerService.logIn(this.credentials).subscribe(      
      data => {
        localStorage.setItem('jwtToken', data)
        let tokeninfo = this.getDecodedAccessToken(data)
        localStorage.setItem('username', tokeninfo.sub)
        localStorage.setItem('role', tokeninfo.role)
        localStorage.setItem('exp', tokeninfo.exp)
        console.log('Dobio: ', data)
        this.router.navigateByUrl('/').then(() => {
          window.location.reload();
        });
      },
      error => {
        console.log('Error!', error)
        this._snackBar.open('Invalid username or password', 'Close', {duration: 3000});
      }
    )
  }

  getDecodedAccessToken(token: string): any {
    try{
        return jwt_decode(token);
    }
    catch(Error){
        return '';
    }
  }

}
