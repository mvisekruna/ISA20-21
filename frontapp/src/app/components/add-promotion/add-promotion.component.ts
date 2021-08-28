import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddPromoRequest } from 'src/app/model/add-promo-request';
import { AddPromotionService } from 'src/app/service/add-promotion.service';

@Component({
  selector: 'app-add-promotion',
  templateUrl: './add-promotion.component.html',
  styleUrls: ['./add-promotion.component.css']
})
export class AddPromotionComponent implements OnInit {

  pr: AddPromoRequest;
  filterDateFrom: Date;
  filterDateTo: Date;


  constructor(private addPromoService: AddPromotionService, private router: Router) { }

  ngOnInit(): void {
  this.pr = new AddPromoRequest;
      this.filterDateFrom = new Date();
      this.filterDateTo = new Date();


  }

  onSubmit(){
    console.log(this.pr)
    this.filterDateFrom = new Date((<HTMLInputElement>document.getElementById("startPromo")).value);
    this.pr.startPromo = this.filterDateFrom.toISOString().slice(0, 16).replace('T', ' ')+":00";
    this.filterDateTo = new Date((<HTMLInputElement>document.getElementById("endPromo")).value);
    this.pr.endPromo = this.filterDateTo.toISOString().slice(0, 16).replace('T', ' ')+":00";
   
    this.addPromoService.addPromotion(this.pr).subscribe( res => {
      console.log(res);
      this.router.navigate(["/homepage"]);
    });
  }

  get startPromo():string
  {
    //Strip the timezone letter 'Z' from the string;
    
    return this.filterDateFrom.toISOString().slice(0, 16).replace('T', ' ')+":00";  
  }
  set startPromo(value:string)
  {
    this.filterDateFrom = new Date(value);
  }
  get endPromo():string
  {
    //Strip the timezone letter 'Z' from the string;
    
    return this.filterDateTo.toISOString().slice(0, 16).replace('T', ' ')+":00";  
  }
  set endPromo(value:string)
  {
    this.filterDateTo = new Date(value);
  }
}
