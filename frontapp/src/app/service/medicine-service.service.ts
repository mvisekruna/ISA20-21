import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Medicine } from '../model/medicine';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MedicineServiceService {

  private medicineUrl: string;
  private medicineUrlId: string = 'http://localhost:8080/medicine/id';
  private medicineUrlName: string = 'http://localhost:8080/medicine/name';

  constructor(private http: HttpClient) { 
    this.medicineUrl='http://localhost:8080/medicine';
  }

  public findAll(): Observable<Medicine[]> {
    return this.http.get<Medicine[]>(this.medicineUrl);
  }
}
