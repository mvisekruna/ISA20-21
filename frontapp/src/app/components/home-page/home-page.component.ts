import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  info: any;
  isUser: boolean= false;
  title = 'Pharmacy information system';
  constructor() {

   }

  ngOnInit(): void {
    this.info = {
      token: localStorage.getItem("TOKEN"),
      email: localStorage.getItem("USERNAME"),
      authorities: localStorage.getItem("AUTHORITIES")
    };
    console.log(this.info.token);
    console.log(this.info.authorities);
    if (this.info.authorities == "ROLE_PATIENT"){
      this.isUser=true;
    }
  }
  logout() {
    localStorage.clear();
    window.location.reload();
    this.isUser=false;
  }
}
