import { Injectable } from '@angular/core';
import { Customer } from '../model/customer';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Credentials } from '../model/credentials';
import { Observable, throwError } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
/* import { do } from "rxjs/operators"; */
import { of } from 'rxjs';
import 'rxjs/add/operator/catch';
import { DeleteDto } from '../model/deleteDto';

const headers = { 'content-type': 'application/json'} 

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private _baseUrl = 'http://localhost:8080/';
  private _customerRegistration = this._baseUrl + 'api/customers';
  private _submitRegistration  = this._customerRegistration + '/create';
  private _editCustomer  = this._customerRegistration + '/edit';
  private _getCustomerByUsername  = this._customerRegistration + '/';
  private _deleteCustomer  = this._customerRegistration + '/delete';
  private _login = this._baseUrl + 'auth/login';  
  private _getAllUsernames = this._baseUrl + 'auth/getAllCustomerUsernames';


  returnedCustomer!: Customer;

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
    return this._http.post(this._login, body,{headers, responseType: 'text'})
  }

  /* edit(customer : Customer):Observable<any>{
    const body = JSON.stringify(customer);
    return this._http.put(this._editCustomer, body)
                     .pipe(catchError(this.handleError));
  } */

  edit(customer : Customer):Observable<any>{
    const body = JSON.stringify(customer);
    return this._http.post(this._editCustomer, body)
  }

  getCustomerByUsername(username: string): Observable<Customer> {
    return this._http.get<Customer>(this._getCustomerByUsername + username)
                           .pipe(tap(data =>  console.log('Iz service-a: ', data)),                         
                                catchError(this.handleError)); 
  }
  
  sendDelete(deleteDto: DeleteDto):Observable<any>{
    const body = JSON.stringify(deleteDto);
    return this._http.post(this._deleteCustomer, body )
  }

  getAllUsernames(): Observable<string[]> { 
    return this._http.get<string[]>(this._getAllUsernames)
                          .pipe(tap(data =>  console.log('All: ' + JSON.stringify(data))),
                            catchError(this.handleError)); 
  }



  private handleError(err : HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err.message);
    throw new Error('Method not implemented.');
  } 
}
