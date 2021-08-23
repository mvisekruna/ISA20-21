import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddWorktimeRequest } from 'src/app/model/add-worktime-request';
import { WorktimeServiceService } from 'src/app/service/worktime-service.service';

@Component({
  selector: 'app-add-new-pharmacist-or-dermatologist',
  templateUrl: './add-new-pharmacist-or-dermatologist.component.html',
  styleUrls: ['./add-new-pharmacist-or-dermatologist.component.css']
})
export class AddNewPharmacistOrDermatologistComponent implements OnInit {

  addWorkTimeRequest : AddWorktimeRequest;
  constructor(private workTimeService: WorktimeServiceService, private router: Router) 
  {
    this.addWorkTimeRequest = new AddWorktimeRequest;
   }

  ngOnInit(): void {
  }

  onSubmit(){
    console.log(this.addWorkTimeRequest);
    this.workTimeService.addWorkTime(this.addWorkTimeRequest).subscribe(data => {
      console.log(data);
    })
  }

}
