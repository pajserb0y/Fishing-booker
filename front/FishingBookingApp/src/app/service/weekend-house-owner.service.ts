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

@Injectable({
  providedIn: 'root'
})
export class WeekendHouseOwnerService {

  private _weekendHouseOwnerRegistration = '/api/weekendhouseowners';
  private _submitRegistration  = this._weekendHouseOwnerRegistration + '/create';
  private _getWeekendHouseOwnerByUsername  = this._weekendHouseOwnerRegistration + '/';
  private _getAllUsernames =  '/api/uth/getAllWeekendHouseOwnerUsernames';
  private _editWeekendHouseOwner  = this._weekendHouseOwnerRegistration + '/edit';
  private _allWeekendHouses  = this._weekendHouseOwnerRegistration + '/allWeekendHouses';
  private _findAvailableWeekendHouses  = this._weekendHouseOwnerRegistration + '/findAvailableForDateRange';



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

  findAvailableHousesForSelectedTerm(start: Date, end: Date) : Observable<any> {
    start = new Date(Date.UTC(start.getFullYear(), start.getMonth(), start.getDate(), start.getHours(), start.getMinutes()));
    end = new Date(Date.UTC(end.getFullYear(), end.getMonth(), end.getDate(), end.getHours(), end.getMinutes()));
    const body=JSON.stringify({start, end});
    console.log(body)
    return this._http.post(this._findAvailableWeekendHouses, body)
  }


  private handleError(err : HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err.message);
    throw new Error('Method not implemented.');
  } 
}
