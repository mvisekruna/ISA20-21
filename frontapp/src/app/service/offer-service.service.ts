import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Offer } from '../model/offer';
import { OfferRequest } from '../model/offer-request';

@Injectable({
  providedIn: 'root'
})
export class OfferServiceService {

  constructor(private http: HttpClient) { }


  public createOffer(offerRequest: OfferRequest): Observable<Offer>{
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Offer>('http://localhost:8080/offer', offerRequest, {headers})
  }

  public getOffersForOrder(pharmacyId: any): Observable<Offer[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Offer[]>(`http://localhost:8080/offer/getoffers/${pharmacyId}`, {headers});
  }

  public pickOneOffer(offerId: any): Observable<Offer>{
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Offer>(`http://localhost:8080/offer/pickoffer/${offerId}`, {headers})
  }



}
