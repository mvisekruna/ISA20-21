import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../model/order';
import { OrderRequest } from '../model/order-request';

@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {

  constructor(private http: HttpClient) { }

  public saveOrder(orderRequest: OrderRequest): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Observable<any>>('http://localhost:8080/order/save', orderRequest, {headers});
  }

  public getOrdersFromPharmacy(pharmacyId: any): Observable<Order[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Order[]>(`http://localhost:8080/order/${pharmacyId}`, {headers});
  }
}
