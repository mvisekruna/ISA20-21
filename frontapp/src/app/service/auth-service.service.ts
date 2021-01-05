import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { map } from 'rxjs/operators';
import { ApiServiceService } from './api-service.service';
import { AuthLoginInfo } from '../model/auth-login-info';
import { Observable } from 'rxjs';
import { AuthJwtResponce } from '../model/auth-jwt-responce';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private accesstoken = "";

    loginURL = 'http://localhost:8080/auth/login';
    singupURL = 'http://localhost:8080/auth/signup';
    loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
  constructor(private router: Router, private apiService: ApiServiceService, private http: HttpClient) { }
 
  login(user: User) : Observable<any>{
    console.log('usao u login');
    console.log(user);
    
    return this.apiService.post(this.loginURL, user, this.loginHeaders)
    .pipe(map((res)=>{
      console.log('Login success');
      console.log(res);
      this.accesstoken = res.accessToken;
      
    }));
  }
  // JwtResponse(accessToken,type,username,authorities)
  attemptAuth(credentials: AuthLoginInfo): Observable<any> {
    return this.http.post<AuthJwtResponce>(this.loginURL, credentials, this.httpOptions);
  }
}
