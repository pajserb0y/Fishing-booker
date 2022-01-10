import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BoatOwner } from '../model/boat-owner';
@Injectable({
  providedIn: 'root'
})
export class BoatOwnerService {
  private _baseUrl = 'http://localhost:8080';  
  private _boatOwnerRegistration = this._baseUrl + '/api/boatowners';
  private _submitRegistration  = this._boatOwnerRegistration + '/create';

  constructor(private _http: HttpClient) { }

  createBoatOwner(boatOwner: BoatOwner) : Observable<any> {
    const body=JSON.stringify(boatOwner);
    console.log(body)
    return this._http.post(this._submitRegistration, body)
  } 
}
