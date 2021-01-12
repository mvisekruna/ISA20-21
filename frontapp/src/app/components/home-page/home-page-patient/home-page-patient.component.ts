import { Component, OnInit, Input } from '@angular/core';
import { User } from 'src/app/model/user';
import { UserUpdateRequest } from 'src/app/model/user-update-request';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-home-page-patient',
  templateUrl: './home-page-patient.component.html',
  styleUrls: ['./home-page-patient.component.css']
})
export class HomePagePatientComponent implements OnInit {

 
  info: any;
  userInfo = {
    email: null,
    firstName: null,
    lastName: null,
    address: null,
    city: null,
    state: null,
    phone:  null
  };
  title: string;
  changeInfo: boolean = false;
  changePass: boolean = false;
  userUpdateRequest: UserUpdateRequest;
  passRequest: any = {};
  constructor(private userService: UserServiceService) {
    this.userUpdateRequest=new UserUpdateRequest();
   }

  ngOnInit(): void {
    
    this.title="My profile";
    this.info = {email: localStorage.getItem("USERNAME"),
    t: localStorage.getItem("TOKEN") };
    
    console.log(this.info.email);
    this.userService.getMyInfo(this.info.email)
    .subscribe(data => {
      console.log(data);
      this.userInfo = {
        email: data.email,
        firstName: data.firstName,
        lastName: data.lastName,
        address: data.homeAddress,
        city: data.city,
        state: data.state,
        phone: data.phoneNumber
      };
      console.log(this.userInfo);
  }
  );

  }
  
  changeInfoFun() {
    this.changeInfo=true;
  }

  cancelFun(){
    this.changeInfo=false;
  }

  saveChangeFun(){
    this.userUpdateRequest.firstname = (<HTMLInputElement>document.getElementById("name")).value;
    this.userUpdateRequest.lastname = (<HTMLInputElement>document.getElementById("surname")).value;
    this.userUpdateRequest.address = (<HTMLInputElement>document.getElementById("address")).value;
    this.userUpdateRequest.city = (<HTMLInputElement>document.getElementById("city")).value;
    this.userUpdateRequest.state = (<HTMLInputElement>document.getElementById("state")).value;
    this.userUpdateRequest.phone = (<HTMLInputElement>document.getElementById("phone")).value;
    console.log(this.userUpdateRequest);
    console.log(this.info.t)
    this.userService.updateMyInfo(this.userUpdateRequest, this.info.t )
    .subscribe(data => {
      console.log(data); }
      );
      this.changeInfo=false;
      window.location.reload();

  }

  changePassFun(){
    this.changePass=true;
  }
  saveChangePassFun(){
    this.passRequest.oldPassword = (<HTMLInputElement>document.getElementById("oldpass")).value;
    this.passRequest.newPassword = (<HTMLInputElement>document.getElementById("newpass")).value;
    console.log(this.passRequest);
    this.userService.updateMyPass(this.passRequest, this.info.t)
    .subscribe(data => {
      console.log(data); }
      );
    this.changePass=false;
    window.location.reload();
    // treba uraditi da hendluje erore ako se ne unese ispravna stara sifra i slicno!!!
  }
  cancelPassFun(){
    this.changePass=false;
  }
}
