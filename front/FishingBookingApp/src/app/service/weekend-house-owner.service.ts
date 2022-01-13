import { Injectable } from '@angular/core';
import { Credentials } from '../model/credentials';
import { map, catchError, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WeekendHouse } from '../model/weekend-house';
import { WeekendHouseOwner } from '../model/weekend-house-owner';

/* import { do } from "rxjs/operators"; */
import { of } from 'rxjs';
import 'rxjs/add/operator/catch';
import { FormGroup } from '@angular/forms';
import { WeekendHouseReservation } from '../model/weekend-house-reservation';
import { Term } from '../model/term';
import { WeekendHouseReservationWithDateAsString } from '../model/weekend-house-reservation-with-date-as-string';

@Injectable({
  providedIn: 'root'
})
export class WeekendHouseOwnerService {
 

  private _weekendHouseOwnerRegistration = '/api/weekendhouseowners';
  private _weekendHouseRegistrationController = '/api/weekendHouseReservations';
  private _submitRegistration  = this._weekendHouseOwnerRegistration + '/create';
  private _getWeekendHouseOwnerByUsername  = this._weekendHouseOwnerRegistration + '/';
  private _getAllUsernames =  '/api/auth/getAllWeekendHouseOwnerUsernames';
  private _editWeekendHouseOwner  = this._weekendHouseOwnerRegistration + '/edit';
  private _editWeekendHouse =  this._weekendHouseOwnerRegistration + '/editWeekendHouse';
  private _allWeekendHouses  = this._weekendHouseOwnerRegistration + '/allWeekendHouses';
  private _allWeekendHousesforOwner = this._weekendHouseOwnerRegistration + '/allWeekendHousesForOwner/';
  private _findAvailableWeekendHouses  = this._weekendHouseOwnerRegistration + '/findAvailableForDateRange';
  private _makeReservation = this._weekendHouseRegistrationController + '/reserve';
  private _getFutureReservationsForCustomerUsername = this._weekendHouseRegistrationController + '/getFutureForCustomerUsername/';
  private _addFreeTerm = this._weekendHouseOwnerRegistration + '/_addFreeTerm/';
  private _getAllFreeTermsForWeekendHouse = this._weekendHouseOwnerRegistration + '/getAllFreeTermsForWeekendHouse/';
  private _getAllReservationsForWeekendHouse = this._weekendHouseOwnerRegistration + '/getAllReservationsForWeekendHouse/';


  weekendHouse !: WeekendHouse;
  constructor(private _http: HttpClient) { }

  createWeekendHouseOwner(weekendHouseOwner: WeekendHouseOwner) : Observable<any> {
    const body=JSON.stringify(weekendHouseOwner);
    console.log(body)
    return this._http.post(this._submitRegistration, body)
  } 

  edit(weekendHouseOwner : WeekendHouseOwner):Observable<any>{
    const body = JSON.stringify(weekendHouseOwner);
    return this._http.post(this._editWeekendHouseOwner, body)
  }

  editWeekendHouse(weekendHouse : WeekendHouse):Observable<any>
  {
    const body = JSON.stringify(weekendHouse);
    return this._http.post(this._editWeekendHouse, body)
  }
  
  getWeekendHouseOwnerByUsername(username: string): Observable<WeekendHouseOwner> {
    return this._http.get<WeekendHouseOwner>(this._getWeekendHouseOwnerByUsername + username)
                           .pipe(tap(data =>  console.log('Iz service-a: ', data)),                         
                                catchError(this.handleError)); 
  }

  getAllUsernames(): Observable<string[]> { 
    return this._http.get<string[]>(this._getAllUsernames)
                          .pipe(tap(data =>  console.log('All: ' + JSON.stringify(data))),
                            catchError(this.handleError)); 
  }

  getAllWeekendHouses(): Observable<WeekendHouse[]> {
    return this._http.get<WeekendHouse[]>(this._allWeekendHouses)
                      .pipe(tap(data =>  console.log('Iz service-a: ', data)),                         
                      catchError(this.handleError)); 
  }

  getAllWeekendHousesForOwner(username : String|null): Observable<WeekendHouse[]>
  {
    return this._http.get<WeekendHouse[]>(this._allWeekendHousesforOwner + username)
                      .pipe(tap(data =>  console.log('Iz service-a: ', data)),                         
                      catchError(this.handleError)); 
  }

  findAvailableHousesForSelectedTerm(start: Date, end: Date) : Observable<any> {
    start = new Date(Date.UTC(start.getFullYear(), start.getMonth(), start.getDate(), start.getHours(), start.getMinutes()));
    end = new Date(Date.UTC(end.getFullYear(), end.getMonth(), end.getDate(), end.getHours(), end.getMinutes()));
    const body=JSON.stringify({start, end});
    console.log(body)
    return this._http.post(this._findAvailableWeekendHouses, body)
  }

  reserve(houseReservation: WeekendHouseReservation) : Observable<any>  {
    const body=JSON.stringify(houseReservation);
    console.log(body)
    return this._http.post(this._makeReservation, body)
  }

  getFutureReservationsForCustomerUsername(username : string) : Observable<WeekendHouseReservationWithDateAsString[]>  {
    return this._http.get<WeekendHouseReservationWithDateAsString[]>(this._getFutureReservationsForCustomerUsername + username)
                      .pipe(tap(data =>  console.log('Iz service-a: ', data)),                         
                      catchError(this.handleError)); 
  }

  addFreeTerm(newFreeTerm :Term): Observable<any>
  {
    const body = JSON.stringify(newFreeTerm);
    return this._http.post(this._addFreeTerm, body)
  }

  getAllFreeTermsForWeekendHouse(weekendHouse : WeekendHouse) :Observable<Term[]>
  {
    return this._http.get<Term[]>(this._getAllFreeTermsForWeekendHouse + weekendHouse.id)
                          .pipe(tap(data =>  console.log('All: ' + JSON.stringify(data))),
                            catchError(this.handleError)); 
  }

  getAllReservationsForWeekendHouse(weekendHouse: WeekendHouse) :Observable<WeekendHouseReservation[]> {
    return this._http.get<WeekendHouseReservation[]>(this._getAllReservationsForWeekendHouse + weekendHouse.id)
                          .pipe(tap(data =>  console.log('All: ' + JSON.stringify(data))),
                            catchError(this.handleError)); 
  }

  private handleError(err : HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err.message);
    throw new Error('Method not implemented.');
  } 
}