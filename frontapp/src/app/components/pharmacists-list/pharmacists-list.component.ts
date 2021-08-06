import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-pharmacists-list',
  templateUrl: './pharmacists-list.component.html',
  styleUrls: ['./pharmacists-list.component.css']
})
export class PharmacistsListComponent implements OnInit {

  title: string;
  isPharmacyAdmin=false;
  dtOptions: any = {};
  pharmacists: User[];

  constructor(private userService: UserServiceService) { }

  ngOnInit(): void {
    this.pharmacists=[];
    this.title = "Pharmacists";
    this.userService.getAllPharmacists().subscribe(data => {
      this.pharmacists = data;
      console.log(data);
    });
  }

}
