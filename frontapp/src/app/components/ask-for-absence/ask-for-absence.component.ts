import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CreateAbsenceRequest } from 'src/app/model/create-absence-request';
import { AbsenceServiceService } from 'src/app/service/absence-service.service';

@Component({
  selector: 'app-ask-for-absence',
  templateUrl: './ask-for-absence.component.html',
  styleUrls: ['./ask-for-absence.component.css']
})
export class AskForAbsenceComponent implements OnInit {

  createAbsenceRequest: CreateAbsenceRequest;

  constructor(private absenceService: AbsenceServiceService, private router: Router) {
    this.createAbsenceRequest = new CreateAbsenceRequest;
   }

  ngOnInit(): void {
  }

  onSubmit(){
    this.absenceService.sendRequestForAbsence(this.createAbsenceRequest).subscribe(data =>{
      console.log(data);
    })
  }

}
