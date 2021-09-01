import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MonthlyReport } from '../model/monthly-report';

@Injectable({
  providedIn: 'root'
})
export class MonthlyReportServiceService {

  constructor(private http: HttpClient) { }



  public countExaminations(): Observable<MonthlyReport> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<MonthlyReport>('http://localhost:8080/monthlyreport/count', {headers});
  }
}
