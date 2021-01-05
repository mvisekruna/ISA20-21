import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  info: any;
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
  }
  logout() {
    localStorage.clear();
    window.location.reload();
  }
}
