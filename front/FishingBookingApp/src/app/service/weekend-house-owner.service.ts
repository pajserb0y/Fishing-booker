import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { WeekendHouse } from '../model/weekend-house';
import { WeekendHouseOwner } from '../model/weekend-house-owner';
import { map, catchError, tap } from 'rxjs/operators';
/* import { do } from "rxjs/operators"; */
import { of } from 'rxjs';
import 'rxjs/add/operator/catch';

@Injectable({
  providedIn: 'root'
})
export class WeekendHouseOwnerService {

 // private _baseUrl = 'http://localhost:8080';  
  private _weekendHouseOwnerRegistration =/* this._baseUrl +*/ '/api/weekendhouseowners';
  private _submitRegistration  = this._weekendHouseOwnerRegistration + '/create';
  private _allWeekendHouses  = this._weekendHouseOwnerRegistration + '/allWeekendHouses';


  constructor(private _http: HttpClient) { }

  createWeekendHouseOwner(weekendHouseOwner: WeekendHouseOwner) : Observable<any> {
    const body=JSON.stringify(weekendHouseOwner);
    console.log(body)
    return this._http.post(this._submitRegistration, body)
  } 

  getAllWeekendHouses(): Observable<WeekendHouse[]> {
    return this._http.get<WeekendHouse[]>(this._allWeekendHouses)
                      .pipe(tap(data =>  console.log('Iz service-a: ', data)),                         
                      catchError(this.handleError)); 
  }




  private handleError(err : HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err.message);
    throw new Error('Method not implemented.');
  } 
}
