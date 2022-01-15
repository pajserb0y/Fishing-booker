import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Credentials } from '../model/credentials';
import { Observable, throwError } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BoatOwner } from '../model/boat-owner';
import { Boat } from '../model/boat';
import { TermBoat } from '../model/term-boat';
import { BoatReservation } from '../model/boat-reservation';
import { BoatReservationWithDateAsString } from '../model/boat-reservation-with-date-as-string';
import { BoatFeedback } from '../model/boat-feedback';
import { BoatComplaint } from '../model/boat-complaint';
@Injectable({
  providedIn: 'root'
})
export class BoatOwnerService {
  
  private _baseUrl = 'http://localhost:8080';  
  private _boatOwnerRegistration = this._baseUrl + '/api/boatowners';
  private _boatsReservationController = '/api/boatReservations';
  private _submitRegistration  = this._boatOwnerRegistration + '/create'; 
  private _getBoatOwnerByUsername  = this._boatOwnerRegistration + '/';
  private _getAllUsernames = this._baseUrl + 'auth/getAllBoatOwnerUsernames';
  private _editBoatOwner  = this._boatOwnerRegistration + '/edit';
  private _allBoats  = this._boatOwnerRegistration + '/allBoats';
  private _getAllFreeTermsForBoat = this._boatOwnerRegistration + '/getAllFreeTermsForBoat/';
  private _findAvailableBoats  = this._boatOwnerRegistration + '/findAvailableForDateRange';
  private _makeReservation = this._boatsReservationController + '/reserve';
  private _cancelReservation = this._boatsReservationController + '/cancel/';
  private _getFutureReservationsForCustomerUsername = this._boatsReservationController + '/getFutureForCustomerUsername/';
  private _getPastReservationsForCustomerUsername = this._boatsReservationController + '/getPastForCustomerUsername/';
  private _sendFeedback = this._boatsReservationController + '/sendFeedback';
  private _sendComplaint = this._boatsReservationController + '/sendComplaint';
  


  constructor(private _http: HttpClient) { }


  sendComplaint(complaint: BoatComplaint) : Observable<any> {
    const body=JSON.stringify(complaint);
    console.log(body)
    return this._http.post(this._sendComplaint, body)
  }
  sendFeedback(feedback: BoatFeedback) : Observable<any> {
    const body=JSON.stringify(feedback);
    console.log(body)
    return this._http.post(this._sendFeedback, body)
  }
  getPastReservationsForCustomerUsername(username: string) : Observable<BoatReservationWithDateAsString[]> {
    return this._http.get<BoatReservationWithDateAsString[]>(this._getPastReservationsForCustomerUsername + username)
                      .pipe(tap(data =>  console.log('Iz service-a: ', data)),                         
                      catchError(this.handleError)); 
  }

  cancelReservation(id: number) {
    return this._http.post(this._cancelReservation + id, {})
  }
  getFutureReservationsForCustomerUsername(username: string) {
    return this._http.get<BoatReservationWithDateAsString[]>(this._getFutureReservationsForCustomerUsername + username)
                      .pipe(tap(data =>  console.log('Iz service-a: ', data)),                         
                      catchError(this.handleError)); 
  }
  
  reserve(boatReservation: BoatReservation): Observable<any> {
    const body=JSON.stringify(boatReservation);
    console.log(body)
    return this._http.post(this._makeReservation, body)
  }

  findAvailableBoatsForSelectedTerm(start: Date, end: Date) : Observable<any> {
    start = new Date(Date.UTC(start.getFullYear(), start.getMonth(), start.getDate(), start.getHours(), start.getMinutes()));
    end = new Date(Date.UTC(end.getFullYear(), end.getMonth(), end.getDate(), end.getHours(), end.getMinutes()));
    const body=JSON.stringify({start, end});
    console.log(body)
    return this._http.post(this._findAvailableBoats, body)
  }  
  
  getAllFreeTermsForBoat(selectedBoatInfo: Boat) :Observable<TermBoat[]>{
    return this._http.get<TermBoat[]>(this._getAllFreeTermsForBoat + selectedBoatInfo.id)
                          .pipe(tap(data =>  console.log('All: ' + JSON.stringify(data))),
                            catchError(this.handleError)); 
  }

  getAllBoats(): Observable<Boat[]> {
    return this._http.get<Boat[]>(this._allBoats)
                      .pipe(tap(data =>  console.log('Iz service-a: ', data)),                         
                      catchError(this.handleError)); 
  }

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
