import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Absence } from 'src/app/model/absence';
import { User } from 'src/app/model/user';
import { AbsenceServiceService } from 'src/app/service/absence-service.service';

@Component({
  selector: 'app-absences-list',
  templateUrl: './absences-list.component.html',
  styleUrls: ['./absences-list.component.css']
})
export class AbsencesListComponent implements OnInit {

  titleAbsence: String;
  absences: Absence[];
  pharmacist: User;
  dermatologist:User;
  isPharmacyAdmin: boolean = false;
  isSystemAdmin: boolean;
  constructor(private absenceService: AbsenceServiceService, private router:Router ) { 
    this.pharmacist = new User;
    this.dermatologist = new User;
    this.isPharmacyAdmin = false;
    this.isSystemAdmin = false;
  }

  ngOnInit(): void {
    this.titleAbsence = 'List of requests for absence';
    if (localStorage.getItem('AUTHORITIES')=='ROLE_PHARMACY_ADMIN'){
      this.isPharmacyAdmin = true;
      this.absenceService.findAllRequestsFromPharmacists().subscribe(data=>{
        console.log(data);
        this.absences=data;
      })
    } else if (localStorage.getItem('AUTHORITIES')=='ROLE_SYSTEM_ADMIN') {
      this.isSystemAdmin = true;
      this.absenceService.findAllRequestsFromDermatologists().subscribe(data=>{
        this.absences=data;
      })
    }
  }

  onSubmitApprovedPharmacist(id:any){
    this.pharmacist.id = id;
    this.absenceService.absenceApprovalForPharmacist(this.pharmacist.id).subscribe(data => {
      console.log(data);
    })
  }

  onSubmitDeniedPharmacist(id:any){
    this.pharmacist.id = id;
    this.absenceService.absenceDeniedForPharmacist(this.pharmacist.id).subscribe(data => {
      console.log(data);
    })
  }

  onSubmitApprovedDermatologst(id:any){
    this.dermatologist.id = id;
    this.absenceService.absenceApprovalForDermatologist(this.dermatologist.id).subscribe(data => {
      console.log(data);
    })
  }

  onSubmitDeniedDermatologist(id:any){
    this.dermatologist.id = id;
    this.absenceService.absenceDeniedForDermatologist(this.dermatologist.id).subscribe(data => {
      console.log(data);
    })
  }


}
