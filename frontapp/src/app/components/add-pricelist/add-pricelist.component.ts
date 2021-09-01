import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PricelistRequest } from 'src/app/model/pricelist-request';
import { PricelistServiceService } from 'src/app/service/pricelist-service.service';

@Component({
  selector: 'app-add-pricelist',
  templateUrl: './add-pricelist.component.html',
  styleUrls: ['./add-pricelist.component.css']
})
export class AddPricelistComponent implements OnInit {

  pricelistRequest: PricelistRequest;
  filterDate1: Date;
  filterDate2: Date;
  

  constructor(private pricelistService: PricelistServiceService, private router: Router) {
    this.pricelistRequest = new PricelistRequest;
   }

  ngOnInit(): void {
  }

  onSubmit(){
    this.filterDate1 = new Date((<HTMLInputElement>document.getElementById("fromPeriod")).value);
    this.pricelistRequest.fromPeriod = this.filterDate1.toISOString().slice(0, 16).replace('T', ' ')+":00";

    this.filterDate2 = new Date((<HTMLInputElement>document.getElementById("toPeriod")).value);
    this.pricelistRequest.toPeriod = this.filterDate2.toISOString().slice(0, 16).replace('T', ' ')+":00";


    this.pricelistService.savePricelist(this.pricelistRequest).subscribe(data => {
      console.log(data);
    });
  }

  get fromPeriod(): string {
    return this.filterDate1.toISOString().slice(0, 16).replace('T', ' ')+":00";
  }
  set fromPeriod(value: string) {
    this.filterDate1 = new Date(value);
  }

  get toPeriod(): string {
    return this.filterDate2.toISOString().slice(0, 16).replace('T', ' ')+":00";
  }
  set toPeriod(value: string) {
    this.filterDate2 = new Date(value);
  }

}
