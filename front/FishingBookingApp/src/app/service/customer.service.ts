import { Injectable } from '@angular/core';
import { Customer } from '../model/customer';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credentials } from '../model/credentials';
/* import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch'; */
/* import 'rxjs/add/operator/of';
import 'rxjs/add/operator/map'; */
/* import { of } from 'rxjs';
import { map, switchMap, catchError, mergeMap } from 'rxjs/operators'; */

const headers = { 'content-type': 'application/json'} 

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private _baseUrl = 'http://localhost:8080/';
  private _patientRegistration = this._baseUrl + 'api/customers';
  private _submitRegistration  = this._patientRegistration + '/create';
  private _authenticate        = this._patientRegistration + '/authenticate';

  constructor(private _http: HttpClient) { }

  createCustomer(customer: Customer) : Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Headers': 'Content-Type',
      'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT',
   });
    const body=JSON.stringify(customer);
    console.log(body)
    return this._http.post(this._submitRegistration, body, {'headers':headers})
  }   

  logIn(credentials: Credentials): Observable<any> {
    const body=JSON.stringify(credentials);
    console.log(body)
    return this._http.post(this._authenticate, body,{headers, responseType: 'text'})
  }


  private handleError(err : HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err.message);
    throw new Error('Method not implemented.');
  }
}
