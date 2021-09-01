import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Pricelist } from 'src/app/model/pricelist';
import { PricelistRequest } from 'src/app/model/pricelist-request';
import { PricelistServiceService } from 'src/app/service/pricelist-service.service';

@Component({
  selector: 'app-pricelist-list',
  templateUrl: './pricelist-list.component.html',
  styleUrls: ['./pricelist-list.component.css']
})
export class PricelistListComponent implements OnInit {

  pricelistRequest: PricelistRequest;
  pricelists: Pricelist[];
  constructor(private pricelistService: PricelistServiceService, private router: Router) { 
    this.pricelistRequest = new PricelistRequest;
  }

  ngOnInit(): void {
    console.log(localStorage.getItem('USERNAME'));
    this.pricelistService.findAllFromOnePharmacy().subscribe(data => {
      
      console.log(data);
      this.pricelists  = data;
    })


  }

}
