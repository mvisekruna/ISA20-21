import { Component, OnInit, Optional } from '@angular/core';
import { delay } from 'rxjs/operators';
import { Examination } from 'src/app/model/examination';
import { Pharmacy } from 'src/app/model/pharmacy';
import { ScheduleExaminationRequest } from 'src/app/model/schedule-examination-request';
import { ExaminationServiceService } from 'src/app/service/examination-service.service';
import { PharmacyServiceService } from 'src/app/service/pharmacy-service.service';

@Component({
  selector: 'app-pharmacy-list',
  templateUrl: './pharmacy-list.component.html',
  styleUrls: ['./pharmacy-list.component.css']
})
export class PharmacyListComponent implements OnInit {

  dtOptions: any = {};

  pharmacys: Pharmacy[];
  title: string;
  showPharm : boolean = false;
  pharmacy1: Pharmacy;
  examinations: Examination[];
  scheduleExam: any;
  //terms="";
  scheduleExaminationRequest: ScheduleExaminationRequest;
  constructor(private pharmacyService: PharmacyServiceService, private examService: ExaminationServiceService) { 
  }
  
  ngOnInit(): void {
    
    this.pharmacy1 = new Pharmacy();
    this.title='Pharmacy list';
    this.pharmacyService.findAll().subscribe(data => {
      this.pharmacys = data;
    });
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
    lengthMenu : [5, 10, 25],
      processing: true
    };
    
  }
  showPharmacy(name: string){
    if (localStorage.getItem("TOKEN")!=null){
    console.log(name);
    this.pharmacyService.getPharmacyInfo(name)
    .subscribe(data => {
      console.log(data);
      this.pharmacy1=data
      this.title=data.name;
      console.log(this.pharmacy1);
      this.examService.findAllAvaiable(this.pharmacy1.id).subscribe(data => {
        this.examinations = data;
      });
    }
    );
    //await delay(1100);
    this.showPharm= true;
  }
  }
  scheduleExamination(id: number){
      //napraviti nservis i povati
      if (localStorage.getItem("USERNAME")!=null){
      this.scheduleExaminationRequest = {
        examId: id,
        patientEmail: ""+localStorage.getItem("USERNAME")
      };
      console.log(this.scheduleExaminationRequest);
      this.examService.scheduleExam(this.scheduleExaminationRequest).subscribe( data =>{

      });
      }
      location.reload();
  }

backFun(){
  this.title='Pharmacy list';
  this.showPharm=false;
  //this.title='Pharmacy list';
}
}
