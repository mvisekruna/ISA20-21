import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams  } from '@angular/common/http';
import { Pharmacy } from '../model/pharmacy';
import { User } from '../model/user';
import { Medicine } from '../model/medicine';
@Injectable(
)
export class PharmacyServiceService {
 
  private pharmacyUrl: string;
  private pharmacyUrlId: string = 'http://localhost:8080/pharmacy/id';
  private pharmacyUrlName: string;
  private pharmacyUrlDeleteDermatologist: string;
  private pharmacyUrlDeletePharmacist :string;
  
  constructor(private http: HttpClient) {
   this.pharmacyUrl= 'http://localhost:8080/pharmacy';
   this.pharmacyUrlName= 'http://localhost:8080/pharmacy/name';
   this.pharmacyUrlDeleteDermatologist = 'http://localhost:8080/pharmacy/deletePharmDermatologist'
   this.pharmacyUrlDeletePharmacist = 'http://localhost:8080/pharmacy/deletePharmPharmacist'
   }

  public findAll(): Observable<Pharmacy[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Pharmacy[]>(this.pharmacyUrl, {headers});
  }
  public createPharmacy(body: any): Observable<Pharmacy> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Pharmacy>(this.pharmacyUrl, body, {headers})
  }  
   public getPharmacyInfo(pharmacyName: string): Observable<any>{
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<Pharmacy>(this.pharmacyUrlName, pharmacyName, {headers});
   }
   public getPharmacists(id: number): Observable<User[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<User[]>(`${this.pharmacyUrl}/getPharmacists/${id}`, {headers});
  }
  public getPharmacyWithMed(id: number): Observable<Pharmacy[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Pharmacy[]>(`${this.pharmacyUrl}/havemedicine/${id}`, {headers});
  }
  public setAdmin(body: any): Observable<Pharmacy> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Pharmacy>(`${this.pharmacyUrl}/setadmin`, body, {headers});
  }


  public getDermatologists(id: number): Observable<User[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<User[]>(`${this.pharmacyUrl}/getDermatologists/${id}`, {headers});
  }

  public getMedicinesFromPharmacy(id: number): Observable<Medicine[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Medicine[]>(`${this.pharmacyUrl}/getMedicines/${id}`, {headers});
  }

  public deleteDermatologistFromPharmacy(id: number, dermatologistSurname: string): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.pharmacyUrlDeleteDermatologist}/${id}/${dermatologistSurname}`, {headers});
    
  }

  public deletePharmacistFromPharmacy(id: number, pharmacistSurname: string): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.pharmacyUrlDeletePharmacist}/${id}/${pharmacistSurname}`, {headers});
    
  }

  public deleteMedicineFromPharmacy(id: number, medName: string): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.pharmacyUrl}/deletePharmMedicine/${id}/${medName}`, {headers});
    
  }
  


}
