import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { WeekendHouseOwner } from '../model/weekend-house-owner';

@Injectable({
  providedIn: 'root'
})
export class WeekendHouseOwnerService {

 // private _baseUrl = 'http://localhost:8080';  
  private _weekendHouseOwnerRegistration =/* this._baseUrl +*/ '/api/weekendhouseowners';
  private _submitRegistration  = this._weekendHouseOwnerRegistration + '/create';

  constructor(private _http: HttpClient) { }

  createWeekendHouseOwner(weekendHouseOwner: WeekendHouseOwner) : Observable<any> {
    const body=JSON.stringify(weekendHouseOwner);
    console.log(body)
    return this._http.post(this._submitRegistration, body)
  } 
}
