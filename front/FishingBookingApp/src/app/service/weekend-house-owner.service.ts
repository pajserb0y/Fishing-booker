import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Credentials } from '../model/credentials';
import { Observable, throwError } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { WeekendHouseOwner } from '../model/weekend-house-owner';

@Injectable({
  providedIn: 'root'
})
export class WeekendHouseOwnerService {

 private _baseUrl = 'http://localhost:8080';  
  private _weekendHouseOwnerRegistration = this._baseUrl + '/api/weekendhouseowners';
  private _submitRegistration  = this._weekendHouseOwnerRegistration + '/create';
  private _getWeekendHouseOwnerByUsername  = this._weekendHouseOwnerRegistration + '/';
  private _getAllUsernames = this._baseUrl + 'auth/getAllWeekendHouseOwnerUsernames';
  private _editWeekendHouseOwner  = this._weekendHouseOwnerRegistration + '/edit';

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

  private handleError(err : HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err.message);
    throw new Error('Method not implemented.');
  } 
}
