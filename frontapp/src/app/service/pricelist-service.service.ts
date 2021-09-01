import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pricelist } from '../model/pricelist';
import { PricelistRequest } from '../model/pricelist-request';
import { UpdatePricelistRequest } from '../model/update-pricelist-request';

@Injectable({
  providedIn: 'root'
})
export class PricelistServiceService {

  constructor(private http: HttpClient) { }

  public findById(pricelistId: any): Observable<Pricelist> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Pricelist>(`http://localhost:8080/pricelist/findbyid/${pricelistId}`, {headers})
  }

  public savePricelist(pricelistRequest: PricelistRequest): Observable<Pricelist>{
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Pricelist>('http://localhost:8080/pricelist/save', pricelistRequest, {headers});
  }

  public updatePricelist(pricelistRequest: UpdatePricelistRequest): Observable<Pricelist>{
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Pricelist>('http://localhost:8080/pricelist/update', pricelistRequest, {headers});
  }

}
