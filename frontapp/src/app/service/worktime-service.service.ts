import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddWorktimeRequest } from '../model/add-worktime-request';

@Injectable({
  providedIn: 'root'
})
export class WorktimeServiceService {

  private workTimeUrl: string;


  constructor(private http: HttpClient) {

  }

  public addWorkTimeDermatologist(addWorkTimeRequest: AddWorktimeRequest): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Observable<any>>('http://localhost:8080/pharmacy/setdermatologist' , addWorkTimeRequest, {headers});
  
  }

  public addWorkTimePharmacist(addWorkTimeRequest: AddWorktimeRequest): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Observable<any>>('http://localhost:8080/pharmacy/setpharmacist' , addWorkTimeRequest, {headers});
  
  }
  
}
