import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CanUnscheduleExamination } from '../model/can-unschedule-examination';
import { Examination } from '../model/examination';
import { ScheduleExaminationRequest } from '../model/schedule-examination-request';

@Injectable({
  providedIn: 'root'
})
export class ExaminationServiceService {

  private examUrl: string;
  private scheduleExamUrl: string;
  private unscheduleExamUrl: string;

  
  constructor(private http: HttpClient) {
   this.examUrl= 'http://localhost:8080/examination/pharm';
   this.scheduleExamUrl ='http://localhost:8080/examination/schedule';
   this.unscheduleExamUrl ='http://localhost:8080/examination/unschedule';

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
   public getSchedule(): Observable<CanUnscheduleExamination[]>{
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<any>(this.scheduleExamUrl, {headers});
   }
   public unschedule(ser: ScheduleExaminationRequest): Observable<Examination>{
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
     return this.http.post<Examination>(this.unscheduleExamUrl, ser, {headers});
   }
   
}
