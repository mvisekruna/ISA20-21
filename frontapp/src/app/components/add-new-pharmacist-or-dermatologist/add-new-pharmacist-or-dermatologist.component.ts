import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddWorktimeRequest } from 'src/app/model/add-worktime-request';
import { User } from 'src/app/model/user';
import { UserServiceService } from 'src/app/service/user-service.service';
import { WorktimeServiceService } from 'src/app/service/worktime-service.service';

@Component({
  selector: 'app-add-new-pharmacist-or-dermatologist',
  templateUrl: './add-new-pharmacist-or-dermatologist.component.html',
  styleUrls: ['./add-new-pharmacist-or-dermatologist.component.css']
})
export class AddNewPharmacistOrDermatologistComponent implements OnInit {

  addWorkTimeRequest : AddWorktimeRequest;
 
  user : any;
  constructor(private workTimeService: WorktimeServiceService, private userService:UserServiceService, private router: Router ) 
  {
    this.addWorkTimeRequest = new AddWorktimeRequest;
   }

  ngOnInit(): void {
  }

  onSubmit(){
    console.log(this.addWorkTimeRequest.pharmOrDermEmail);
    console.log(this.addWorkTimeRequest.pharmacyId);
    this.userService.getMyInfo(this.addWorkTimeRequest.pharmOrDermEmail).subscribe( data=>{
      this.user=data;
      console.log(this.user);
      if(this.user.ROLE_DERMATOLOGIST){
        this.workTimeService.addWorkTimeDermatologist(this.addWorkTimeRequest).subscribe(data => {
          console.log(data);
        });

      } else {
        this.workTimeService.addWorkTimePharmacist(this.addWorkTimeRequest).subscribe(data => {
          console.log(data);
        });
      }
    });
  }

}
