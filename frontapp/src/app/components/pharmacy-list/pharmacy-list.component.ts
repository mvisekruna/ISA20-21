import { Component, OnInit, Optional } from '@angular/core';
import { Router } from '@angular/router';
import { delay } from 'rxjs/operators';
import { Examination } from 'src/app/model/examination';
import { Medicine } from 'src/app/model/medicine';
import { Pharmacy } from 'src/app/model/pharmacy';
import { ScheduleExaminationRequest } from 'src/app/model/schedule-examination-request';
import { User } from 'src/app/model/user';
import { ExaminationServiceService } from 'src/app/service/examination-service.service';
import { PharmacyServiceService } from 'src/app/service/pharmacy-service.service';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-pharmacy-list',
  templateUrl: './pharmacy-list.component.html',
  styleUrls: ['./pharmacy-list.component.css']
})
export class PharmacyListComponent implements OnInit {

  

  dtOptions: any = {};
  isAdmin: boolean = false;
  pharmacys: Pharmacy[];
  title: string;
  showPharm : boolean = false;
  pharmacy1: Pharmacy;
  examinations: Examination[];
  scheduleExam: any;
  freeAdmins: User[];
  pharmAdmin: User;
  body: any;
  isPharmacyAdmin: boolean = false;
  dermatologists : User[];
  pharmacists : User[];
  medicines : Medicine[];
  //terms="";
  scheduleExaminationRequest: ScheduleExaminationRequest;
  constructor(private pharmacyService: PharmacyServiceService,
     private examService: ExaminationServiceService,
     private userService: UserServiceService,
     private route: Router) { 
       this.freeAdmins=[];
       this.pharmAdmin = new User;
       this.body = {
          pharmacyId: Number,
          adminEmail: ""
       };
  
  }
  
  ngOnInit(): void {
    this.pharmacys=[];
    
    if (localStorage.getItem("AUTHORITIES")=="ROLE_SYSTEM_ADMIN"){
      this.isAdmin=true;
      this.userService.getFreePharmacyAdmin().subscribe( data => {
         this.freeAdmins=data;
         console.log(data);
      });
    } else {this.isAdmin=false;
    this.isPharmacyAdmin=true;}
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
      this.pharmacyService.getDermatologists(this.pharmacy1.id).subscribe(data => {
        this.dermatologists = data;
      })

      this.pharmacyService.getPharmacists(this.pharmacy1.id).subscribe(data => {
        this.pharmacists = data;
      })

      this.pharmacyService.getMedicinesFromPharmacy(this.pharmacy1.id).subscribe(data => {
        this.medicines = data;
      })
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
makeAdmin(){
  console.log(this.pharmAdmin.email);
  console.log(this.pharmacy1.id);
  this.body.pharmacyId=this.pharmacy1.id;
  this.body.adminEmail=this.pharmAdmin.email;
  this.pharmacyService.setAdmin(this.body).subscribe( data => {
    this.route.navigate(["/pharmacy-list"]);
  });
}

removeDermatologistFromList(id:number, dermatologistSurname: string){
  console.log(id);
  console.log(dermatologistSurname); 
  this.pharmacyService.deleteDermatologistFromPharmacy(id, dermatologistSurname).subscribe(data =>{
    console.log(data);
  })
}

removePharmacistFromList(id:number, pharmacistSurname: string){
  console.log(id);
  console.log(pharmacistSurname); 
  this.pharmacyService.deletePharmacistFromPharmacy(id, pharmacistSurname).subscribe(data =>{
    console.log(data);
  })
}

removeMedicineFromList(id:number, medName: string){
  console.log(id);
  console.log(medName); 
  this.pharmacyService.deleteMedicineFromPharmacy(id, medName).subscribe(data =>{
    console.log(data);
  })
}

}
