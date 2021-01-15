import { Component, OnInit } from '@angular/core';
import { Allergies } from 'src/app/model/allergies';
import { AllergiesServiceService } from 'src/app/service/allergies-service.service';

@Component({
  selector: 'app-allergies-list',
  templateUrl: './allergies-list.component.html',
  styleUrls: ['./allergies-list.component.css']
})
export class AllergiesListComponent implements OnInit {

  
  title: string;
  info: any;
  allergies: Allergies[];
  constructor(private allergiesService: AllergiesServiceService) { 
    this.title='My allergies';
  }

  ngOnInit(): void {
    this.info = {email: localStorage.getItem("USERNAME"),
    t: localStorage.getItem("TOKEN") };
    this.allergiesService.getMedicinesForPatient(this.info.t)
    .subscribe(data => {
      this.allergies=data;
    });
  }

}
