import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ApiServiceService } from './api-service.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  currentUser: User;
  private userUrl: string;

  constructor(private apiService: ApiServiceService,
    private http: HttpClient) {
    this.userUrl= 'http://localhost:8080/api'; // api/user...
   }
   public getMyInfo(email: string) : Observable<any>{
    console.log(email);
    
      const headers = new HttpHeaders({'Content-Type': 'application/json'});
      return this.http.post(`${this.userUrl}/{email}`,email, {headers});    

  }
}
