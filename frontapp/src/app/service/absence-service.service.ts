import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Absence } from '../model/absence';
import { CreateAbsenceRequest } from '../model/create-absence-request';

@Injectable({
  providedIn: 'root'
})
export class AbsenceServiceService {

  constructor(private http: HttpClient) {

  }


  public findAllRequestsFromPharmacists(): Observable<Absence[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Absence[]>('http://localhost:8080/absence/pharmacists', {headers});
  }

  public findAllRequestsFromDermatologists(): Observable<Absence[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Absence[]>('http://localhost:8080/absence/dermatologists', {headers});
  }

  public sendRequestForAbsence(createAbsenceRequest: CreateAbsenceRequest): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Observable<any>>(`http://localhost:8080/absence`, createAbsenceRequest, {headers});
  }

  public absenceApprovalForPharmacist(pharmacistId: any): any {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<any>(`http://localhost:8080/absence/approvedpharmacist/${pharmacistId}`, {}, {headers});
  }

  public absenceDeniedForPharmacist(pharmacistId: any): any {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<any>(`http://localhost:8080/absence/deniedpharmacist/${pharmacistId}`, {}, {headers});
  }

  public absenceApprovalForDermatologist(dermatologistId: any): any {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<any>(`http://localhost:8080/absence/approveddermatologist/${dermatologistId}`, {}, {headers});
  }

  public absenceDeniedForDermatologist(dermatologistId: any): any {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<any>(`http://localhost:8080/absence/denieddermatologist/${dermatologistId}`, {}, {headers});
  }
}
