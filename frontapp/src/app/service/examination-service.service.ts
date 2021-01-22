import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Examination } from '../model/examination';
import { ScheduleExaminationRequest } from '../model/schedule-examination-request';

@Injectable({
  providedIn: 'root'
})
export class ExaminationServiceService {

  private examUrl: string;
  private scheduleExamUrl: string;
  
  constructor(private http: HttpClient) {
   this.examUrl= 'http://localhost:8080/examination/pharm';
   this.scheduleExamUrl ='http://localhost:8080/examination/schedule';
   }

   public findAllAvaiable(pharmId: number): Observable<Examination[]> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
     return this.http.post<Examination[]>(this.examUrl, pharmId, {headers});
   }
   public scheduleExam(ser: ScheduleExaminationRequest): Observable<any>{
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    console.log(ser);
    return this.http.post<Examination>(this.scheduleExamUrl, ser, {headers}); 
     
   }

}
