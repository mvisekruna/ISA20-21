import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CounselingHistoryListComponent } from '../components/counseling-history-list/counseling-history-list.component';
import { CanCancelCounseling } from '../model/can-cancel-counseling';
import { Counseling } from '../model/counseling';
import { ScheduleCounselingRequest } from '../model/schedule-counseling-request';
import { ScheduleExaminationRequest } from '../model/schedule-examination-request';

@Injectable({
  providedIn: 'root'
})
export class CounselingServiceService {

  private scheduleCounUrl: string;
  private unscheduleCounUrl: string;
  private hsitoryCounUrl: string;

  constructor(private http: HttpClient) { 
    this.scheduleCounUrl= 'http://localhost:8080/counseling/schedule';
    this.unscheduleCounUrl= 'http://localhost:8080/counseling/unschedule';
    this.hsitoryCounUrl= 'http://localhost:8080/counseling/history';
  }

  public getSchedule(): Observable<CanCancelCounseling[]>{
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<any>(this.scheduleCounUrl, {headers});
   }
   public unschedule(ser: ScheduleExaminationRequest): Observable<Counseling>{
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
     return this.http.post<Counseling>(this.unscheduleCounUrl, ser, {headers});
   }
   public schedule(scr: ScheduleCounselingRequest): Observable<any>{
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.post<Observable<any>>(this.scheduleCounUrl, scr, {headers});
   }
   public history(): Observable<Counseling[]>{
    const t= localStorage.getItem("TOKEN");
    const headers = new HttpHeaders({'Content-Type': 'application/json'}).set("Authorization", "Bearer " + t);
    return this.http.get<Counseling[]>(this.hsitoryCounUrl, {headers});
   }
  }
