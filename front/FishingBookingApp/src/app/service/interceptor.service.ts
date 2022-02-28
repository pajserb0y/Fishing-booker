import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor(public auth: AuthService) {}

  
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.auth.getToken()}`
      }
    });

    request = request.clone({ headers: request.headers.set('Content-Type', 'application/json') });
    //request = request.clone({ headers: request.headers.set('Access-Control-Allow-Origin', 'http://localhost:4200') });
    //request = request.clone({ headers: request.headers.set('Access-Control-Allow-Headers', 'Content-Type') });
   // request = request.clone({ headers: request.headers.set('Access-Control-Allow-Methods', 'GET,POST,OPTIONS,DELETE,PUT') });

    return next.handle(request);
  }

}
