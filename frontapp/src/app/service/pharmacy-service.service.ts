import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams  } from '@angular/common/http';
import { Pharmacy } from '../model/pharmacy';
import { User } from '../model/user';
import { Medicine } from '../model/medicine';
import { MedicineUpdateRequest } from '../model/medicine-update-request';
import { AddMedicineToPharmacyRequest } from '../model/add-medicine-to-pharmacy-request';
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
    
    return this.http.get<Pharmacy[]>(this.pharmacyUrl);
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
   public setAdmin(body: any): Observable<Pharmacy> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Pharmacy>(`${this.pharmacyUrl}/setadmin`, body, {headers});
  }

  public getPharmacyWithMed(id: number): Observable<Pharmacy[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Pharmacy[]>(`${this.pharmacyUrl}/havemedicine/${id}`, {headers});
  }

  public findPharmacyByAdmin(pharmacyAdminEmail: string): Observable<Pharmacy> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Pharmacy>(`${this.pharmacyUrl}/findpharmacybyadmin/${pharmacyAdminEmail}`, {headers})
  }

  //MEDICINE/////////////////////

  public addMedicineToPharmacy(addMedToPharmacyRequest: AddMedicineToPharmacyRequest): Observable<Pharmacy> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Pharmacy>(`${this.pharmacyUrl}/addmedicinetopharmacy`, addMedToPharmacyRequest, {headers});
  }

  public getMedicinesFromPharmacy(id: any): Observable<Medicine[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Medicine[]>(`${this.pharmacyUrl}/getMedicines/${id}`, {headers});
  }

  public getAllMedicinesFromPharmacy(id: any, medName: string): Observable<Medicine[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Medicine[]>(`${this.pharmacyUrl}/getAllPharmMedicines/${id}/${medName}`, {headers});
  }

  public findOneMedicineFromPharmacy(id: any, medName: string): Observable<Medicine> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Medicine>(`${this.pharmacyUrl}/getOnePharmMedicine/${id}/${medName}`, {headers});
  }

  public updateMedicineFromPharmacy(id: any, medName: string, medicineUpdateRequest: MedicineUpdateRequest): Observable<Medicine> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Medicine>(`${this.pharmacyUrl}/updatePharmMedicine/${id}/${medName}`, {headers});
  }

  public deleteMedicineFromPharmacy(id: any, medName: string): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.pharmacyUrl}/deletePharmMedicine/${id}/${medName}`, {medName}, {headers}); 
  }


  //PHARMACIST///////

   public getPharmacists(id: any): Observable<User[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<User[]>(`${this.pharmacyUrl}/getPharmacists/${id}`, {headers});
  }

  public getAllPharmacistsFromPharmacy(id: any, pharmacistEmail: String): Observable<User[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<User[]>(`${this.pharmacyUrl}/getAllPharmPharmacists/${id}/${pharmacistEmail}`, {headers})
  } 

  public findOnePharmacistFromPharmacy(id: any, pharmacistSurname: String): Observable<User> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<User>(`${this.pharmacyUrl}/getOnePharmPharmacist/${id}/${pharmacistSurname}`, {headers});
  }

  public deletePharmacistFromPharmacy(id: number, pharmacistSurname: string): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.pharmacyUrlDeletePharmacist}/${id}/${pharmacistSurname}`, {pharmacistSurname}, {headers});
    
  }


  //DERMATOLOGIST//////

  public getDermatologists(id: any): Observable<User[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<User[]>(`${this.pharmacyUrl}/getDermatologists/${id}`, {headers});
  }

  public getAllDermatologistsFromPharmacy(id: any, dermEmail: String): Observable<User[]> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<User[]>(`${this.pharmacyUrl}/getAllPharmDermatologists/${id}/${dermEmail}`, {headers});
  }

  public findOneDermatologistFromPharmacy(id: any, dermatologistSurname: String): Observable<User> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<User>(`${this.pharmacyUrl}/getOnePharmDermatologist/${id}/${dermatologistSurname}`, {headers});
  }

  public deleteDermatologistFromPharmacy(id: any, dermatologistSurname: string): Observable<any> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post(`${this.pharmacyUrlDeleteDermatologist}/${id}/${dermatologistSurname}`, {dermatologistSurname}, {headers});
    
  }
}
