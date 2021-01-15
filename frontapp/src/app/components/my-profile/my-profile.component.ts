import { Component, OnInit } from '@angular/core';
import { UserUpdateRequest } from 'src/app/model/user-update-request';
import { UserServiceService } from 'src/app/service/user-service.service';
import { AllergiesServiceService } from 'src/app/service/allergies-service.service';
@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  title: string;
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
  allergies: boolean = false;
  changeInfo: boolean = false;
  changePass: boolean = false;
  userUpdateRequest: UserUpdateRequest;
  medicine: string;
  passRequest: any = {};
  constructor(private userService: UserServiceService, private allergiesService: AllergiesServiceService) {
    this.userUpdateRequest=new UserUpdateRequest();
   }

  ngOnInit(): void {
    this.title="My profile";
    this.info = {email: localStorage.getItem("USERNAME"),
    t: localStorage.getItem("TOKEN") };
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

  addAllergies(){
    this.allergies=true;
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
    this.allergies=false;
  }
  saveAllergiesFun(){
    this.medicine = (<HTMLInputElement>document.getElementById("medicine")).value;
    this.allergiesService.addAllergies(this.medicine, this.info.t)
    .subscribe(data => {
      console.log(data);
    });
    this.allergies=false;
    window.location.reload();
  }

}
