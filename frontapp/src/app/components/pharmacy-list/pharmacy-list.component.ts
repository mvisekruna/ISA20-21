import { Component, OnInit, Optional } from '@angular/core';
import { Pharmacy } from 'src/app/model/pharmacy';
import { PharmacyServiceService } from 'src/app/service/pharmacy-service.service';

@Component({
  selector: 'app-pharmacy-list',
  templateUrl: './pharmacy-list.component.html',
  styleUrls: ['./pharmacy-list.component.css']
})
export class PharmacyListComponent implements OnInit {

  pharmacys: Pharmacy[];
  title: string;
  constructor(private pharmacyService: PharmacyServiceService) { 
    this.title='Pharmacy list';
  }
  
  ngOnInit(): void {
    this.pharmacyService.findAll().subscribe(data => {
      this.pharmacys = data;
    });
  }

}
