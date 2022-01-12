import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Credentials } from '../model/credentials';
import { Observable, throwError } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BoatOwner } from '../model/boat-owner';
@Injectable({
  providedIn: 'root'
})
export class BoatOwnerService {
  private _baseUrl = 'http://localhost:8080';  
  private _boatOwnerRegistration = this._baseUrl + '/api/boatowners';
  private _submitRegistration  = this._boatOwnerRegistration + '/create'; 
  private _getBoatOwnerByUsername  = this._boatOwnerRegistration + '/';
  private _getAllUsernames = this._baseUrl + 'auth/getAllBoatOwnerUsernames';
  private _editBoatOwner  = this._boatOwnerRegistration + '/edit';

  constructor(private _http: HttpClient) { }

  createBoatOwner(boatOwner: BoatOwner) : Observable<any> {
    const body=JSON.stringify(boatOwner);
    console.log(body)
    return this._http.post(this._submitRegistration, body)
  } 
  edit(boatOwner : BoatOwner):Observable<any>{
    const body = JSON.stringify(boatOwner);
    return this._http.post(this._editBoatOwner, body)
  }

  getBoatOwnerByUsername(username: string): Observable<BoatOwner> {
    return this._http.get<BoatOwner>(this._getBoatOwnerByUsername + username)
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
