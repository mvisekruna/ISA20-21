import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QuarterReport } from '../model/quarter-report';

@Injectable({
  providedIn: 'root'
})
export class QuarterReportServiceService {

  constructor(private http: HttpClient) { }

  public countExaminations(): Observable<QuarterReport> {
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<QuarterReport>('http://localhost:8080/quarterreport/count', {headers})

  }
}
