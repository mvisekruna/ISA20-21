import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { map } from 'rxjs/operators';
import { ApiServiceService } from './api-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private accesstoken = "";
    loginURL = 'http://localhost:8080/auth/login';
     body : {
      email: '',
      password: ''
    };
  constructor(private router: Router, private apiService: ApiServiceService) { }

  login(user: User) : any {
    console.log('usao u login');
    console.log(user.email);
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    
    console.log('moze da dodelivrednsot');
    console.log(user);
    console.log(this.loginURL);
    
    return this.apiService.post(this.loginURL, user, loginHeaders)
    .pipe(map((res)=>{
      console.log('Login success');
      console.log(res);
      console.log(res.accessToken)
      this.accesstoken = res.accessToken;

      
    }));
  }
}
