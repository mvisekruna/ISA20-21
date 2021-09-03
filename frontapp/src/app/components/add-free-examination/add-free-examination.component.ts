import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Examination } from 'src/app/model/examination';
import { ExaminationRequest } from 'src/app/model/examination-request';
import { ExaminationServiceService } from 'src/app/service/examination-service.service';

@Component({
  selector: 'app-add-free-examination',
  templateUrl: './add-free-examination.component.html',
  styleUrls: ['./add-free-examination.component.css']
})
export class AddFreeExaminationComponent implements OnInit {

  examinationRequest: ExaminationRequest;
 // examination: Examination;
  constructor(private examinationService: ExaminationServiceService, private router: Router) { 
    this.examinationRequest = new ExaminationRequest;
  }

  ngOnInit(): void {
  }

  onSubmit(){
    this.examinationService.createExamination(this.examinationRequest).subscribe(data =>{
      console.log(data);
   
    })
  }


}
