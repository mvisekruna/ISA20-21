import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddPromoRequest } from '../model/add-promo-request';

@Injectable({
  providedIn: 'root'
})
export class AddPromotionService {

 

  constructor(private http: HttpClient) {

  }
  
  public addPromotion(addPromoRequest: AddPromoRequest): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Observable<any>>('http://localhost:8080/promotion/addPromotion' , addPromoRequest, {headers});
  
  }
}
