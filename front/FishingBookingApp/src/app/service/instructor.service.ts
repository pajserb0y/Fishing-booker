import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Instructor } from '../model/instructor';

@Injectable({
  providedIn: 'root'
})
export class InstructorService {
  // private _baseUrl = 'http://localhost:8080/';  
  private _instructorsRegistration =/* this._baseUrl + */'/api/instructors';
  private _submitRegistration  = this._instructorsRegistration + '/create';

  constructor(private _http: HttpClient) { }

  createInstructor(instructor: Instructor) : Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Headers': 'Content-Type',
      'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT',
   });
    const body=JSON.stringify(instructor);
    console.log(body)
    return this._http.post(this._submitRegistration, body, {'headers':headers})
  } 
}
