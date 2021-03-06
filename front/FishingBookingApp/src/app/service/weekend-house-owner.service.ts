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
import { WeekendHouseTerm } from '../model/term-weekend-house';
import { WeekendHouseReservationWithDateAsString } from '../model/weekend-house-reservation-with-date-as-string';
import { HouseFeedback } from '../model/house-feedback';
import { WeekendHouseWithAvgGrade } from '../model/weekend-house-with-avg-grade';
import { ComplaintWeekendHouse } from '../model/complaint-weekend-house';
import { CustomerReport } from '../model/customer-report';

@Injectable({
  providedIn: 'root'
})
export class WeekendHouseOwnerService {

  private _weekendHouseOwnerRegistration = '/api/weekendhouseowners';
  private _weekendHouseReservationController = '/api/weekendHouseReservations';
  private _submitRegistration  = this._weekendHouseOwnerRegistration + '/create';
  private _getWeekendHouseOwnerByUsername  = this._weekendHouseOwnerRegistration + '/';
  private _getAllUsernames =  '/api/auth/getAllWeekendHouseOwnerUsernames';
  private _editWeekendHouseOwner  = this._weekendHouseOwnerRegistration + '/edit';
  private _editWeekendHouse =  this._weekendHouseOwnerRegistration + '/editWeekendHouse';
  private _allWeekendHouses  = this._weekendHouseOwnerRegistration + '/allWeekendHouses';
  private _allWeekendHousesforOwner = this._weekendHouseOwnerRegistration + '/allWeekendHousesForOwner/';
  private _findAvailableWeekendHouses  = this._weekendHouseOwnerRegistration + '/findAvailableForDateRange';

  private _makeReservation = this._weekendHouseReservationController + '/reserve';
  private _getFutureReservationsForCustomerUsername = this._weekendHouseReservationController + '/getFutureForCustomerUsername/';
  private _getPastReservationsForCustomerUsername = this._weekendHouseReservationController + '/getPastForCustomerUsername/';
  private _cancelReservation = this._weekendHouseReservationController + '/cancel/';
  private _sendFeedback = this._weekendHouseReservationController + '/sendFeedback';
  private _sendComplaint = this._weekendHouseReservationController + '/sendComplaint';
  private _addFreeTerm = this._weekendHouseOwnerRegistration + '/addFreeTerm';      //greska?

  private _makeReservationOrSpecialOffer = this._weekendHouseReservationController + '/reserve';   //gadja isti endpoint kao _makeReservation??

  private _getAllFreeTermsForWeekendHouse = this._weekendHouseOwnerRegistration + '/getAllFreeTermsForWeekendHouse/';
  private _getAllReservationsForWeekendHouse = this._weekendHouseReservationController + '/getAllReservationsForWeekendHouse/';
  private _getCurrentSpecialOffers = this._weekendHouseReservationController + '/getCurrentSpecialOffers/';
  private _removeWeekendHouse = this._weekendHouseOwnerRegistration + '/removeWeekendHouse/';
  private _getAllHouseReservationsForHouseOwner = this._weekendHouseReservationController + '/getAllForWeekendHouseOwner/';
  private _submitReport = this._weekendHouseReservationController + '/reportCustomer';
  private _createWeekendHouse = this._weekendHouseOwnerRegistration + '/createWeekendHouse'


  weekendHouse !: WeekendHouse;
  constructor(private _http: HttpClient) { }


  getCurrentSpecialOffers() : Observable<WeekendHouseReservationWithDateAsString[]>  {
    return this._http.get<WeekendHouseReservationWithDateAsString[]>(this._getCurrentSpecialOffers)
                      .pipe(tap(data =>  console.log('Iz service-a: ', data)),
                      catchError(this.handleError));
  }

  createWeekendHouseOwner(weekendHouseOwner: WeekendHouseOwner) : Observable<any> {
    const body=JSON.stringify(weekendHouseOwner);
    console.log(body)
    return this._http.post(this._submitRegistration, body)
  }

  createWeekendHouse(weekendHouse: WeekendHouse) : Observable<any> {
    const body=JSON.stringify(weekendHouse);
    console.log(body)
    return this._http.post(this._createWeekendHouse, body)
  }

  sendFeedback(feedback: HouseFeedback) : Observable<any> {
    const body=JSON.stringify(feedback);
    console.log(body)
    return this._http.post(this._sendFeedback, body)
  }

  sendComplaint(complaint: ComplaintWeekendHouse) : Observable<any> {
    const body=JSON.stringify(complaint);
    console.log(body)
    return this._http.post(this._sendComplaint, body)
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


  getAllWeekendHouses(): Observable<WeekendHouseWithAvgGrade[]> {
    return this._http.get<WeekendHouseWithAvgGrade[]>(this._allWeekendHouses)
                      .pipe(tap(data =>  console.log('Iz service-a: ', data)),
                      catchError(this.handleError));
  }

  getAllWeekendHousesForOwner(username : String|null): Observable<WeekendHouseWithAvgGrade[]> {
    return this._http.get<WeekendHouseWithAvgGrade[]>(this._allWeekendHousesforOwner + username)
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
    return this._http.post(this._makeReservationOrSpecialOffer, body)
  }

  makeSpecialOffer(specialOffer: WeekendHouseReservation): Observable<any> {
    const body=JSON.stringify(specialOffer);
    console.log(body)
    return this._http.post(this._makeReservationOrSpecialOffer, body)
  }


  cancelReservation(id: number) : Observable<any>{
    return this._http.post(this._cancelReservation + id, {})
  }

  getFutureReservationsForCustomerUsername(username : string) : Observable<WeekendHouseReservationWithDateAsString[]>  {
    return this._http.get<WeekendHouseReservationWithDateAsString[]>(this._getFutureReservationsForCustomerUsername + username)
                      .pipe(tap(data =>  console.log('Iz service-a: ', data)),
                      catchError(this.handleError));
  }

  getPastReservationsForCustomerUsername(username : string) : Observable<WeekendHouseReservationWithDateAsString[]>  {
    return this._http.get<WeekendHouseReservationWithDateAsString[]>(this._getPastReservationsForCustomerUsername + username)
                      .pipe(tap(data =>  console.log('Iz service-a: ', data)),
                      catchError(this.handleError));
  }

  addFreeTerm(newFreeTerm :WeekendHouseTerm): Observable<any> {
    const body = JSON.stringify(newFreeTerm);
    return this._http.post(this._addFreeTerm, body)
  }

  getAllFreeTermsForWeekendHouse(weekendHouse : WeekendHouse) :Observable<WeekendHouseTerm[]> {
    return this._http.get<WeekendHouseTerm[]>(this._getAllFreeTermsForWeekendHouse + weekendHouse.id)
                          .pipe(tap(data =>  console.log('All: ' + JSON.stringify(data))),
                            catchError(this.handleError));
  }

  getAllReservationsForWeekendHouse(weekendHouse: WeekendHouse) :Observable<WeekendHouseReservation[]> {
    return this._http.get<WeekendHouseReservation[]>(this._getAllReservationsForWeekendHouse + weekendHouse.id)
                          .pipe(tap(data =>  console.log('All: ' + JSON.stringify(data))),
                            catchError(this.handleError));
  }

  removeWeekendHouse(weekendHouseId : number) : Observable<any> {
    return this._http.post(this._removeWeekendHouse + weekendHouseId, {})
  }

  getAllReservationsForHouseOwner(username : string) {
    return this._http.get<WeekendHouseReservationWithDateAsString[]>(this._getAllHouseReservationsForHouseOwner + username)
                      .pipe(tap(data =>  console.log('Iz service-a: ', data)),
                      catchError(this.handleError));
  }

  submitReport(report : CustomerReport): Observable<any> {
    const body = JSON.stringify(report)
    return this._http.post(this._submitReport, body)
  }

  private handleError(err : HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err.message);
    throw new Error('Method not implemented.');
  }
}
