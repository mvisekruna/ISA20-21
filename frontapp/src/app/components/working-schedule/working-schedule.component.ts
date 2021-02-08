import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Examination } from 'src/app/model/examination';
import { Medicine } from 'src/app/model/medicine';
import { ScheduleExaminationRequest } from 'src/app/model/schedule-examination-request';
import { User } from 'src/app/model/user';
import { ExaminationServiceService } from 'src/app/service/examination-service.service';
import { MedicineServiceService } from 'src/app/service/medicine-service.service';

@Component({
  selector: 'app-working-schedule',
  templateUrl: './working-schedule.component.html',
  styleUrls: ['./working-schedule.component.css']
})
export class WorkingScheduleComponent implements OnInit {

  ser: ScheduleExaminationRequest;
  currPatient: User;
  showExam= false;
  isDerm= false;
  isExam=false;
  report="";
  dtOptions={};
  medicines: Medicine[];
  examinatons: Examination[];
  exam: Examination;
  scheduleClicked=false;
  constructor(private examService: ExaminationServiceService,
    private medResService: MedicineServiceService,
    private router: Router) { 
    this.examinatons=[];
    this.medicines=[];
    this.exam=new Examination;
    this.currPatient= new User;
    this.ser= new ScheduleExaminationRequest;
  }

  ngOnInit(): void {
    this.exam.patient=new User;
    if(localStorage.getItem("AUTHORITIES")=="ROLE_DERMATOLOGIST"){
      this.examService.getScheduleDerm().subscribe( data => {
        this.examinatons=data;
        console.log(data);
        this.isDerm=true;
      });

    }
  }
  openExam(id: number){
    for (let exams of this.examinatons){
      if (exams.id==id)
      { this.exam.patient=exams.patient;
        this.exam=exams;
        this.currPatient=exams.patient;
        console.log(this.exam.patient.email+ " "+ localStorage.getItem("AUTHORITIES"));
      }
    }
    this.showExam=true;
  }
  cancelExam(){
    console.log(this.exam.patient+ " "+ localStorage.getItem("AUTHORITIES"));
  
    this.examService.cancel(this.exam).subscribe( data => {
      console.log(data);
      this.showExam=false;
      this.ngOnInit();
    });
  }
  startExam(){
    this.medResService.findAll().subscribe( data => {
      this.medicines=data;
    });
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
    lengthMenu : [5, 10, 25],
      processing: true
    };
    this.isExam=true;
  }

  tryPrescribe(id: number){
    this.medResService.tryReservation(this.exam, id).subscribe(data => {
      console.log(data);
      alert(data.message);
    });
  }
  scheduleExam(id: number){
    this.ser.examId=id;
    this.ser.patientEmail=this.currPatient.email;
    this.examService.scheduleExam(this.ser).subscribe( data => {
      console.log(data);// za sad bez provere o slobodnom terminu i zauzetosti pacijesnta
    });
    this.scheduleClicked=true;
  }
  finishExam(){
    this.exam.report=this.report;
    this.examService.finishExam(this.exam).subscribe( data => {
      console.log(data);
      this.scheduleClicked=false;
      this.isExam=false;
      this.router.navigate(["/homepage"]);
    });
    
    
  }
}
