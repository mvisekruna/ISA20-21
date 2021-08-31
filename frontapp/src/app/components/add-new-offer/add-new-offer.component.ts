import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OfferRequest } from 'src/app/model/offer-request';
import { OfferServiceService } from 'src/app/service/offer-service.service';

@Component({
  selector: 'app-add-new-offer',
  templateUrl: './add-new-offer.component.html',
  styleUrls: ['./add-new-offer.component.css']
})
export class AddNewOfferComponent implements OnInit {

  offerRequest: OfferRequest;

  constructor(private offerService: OfferServiceService, private router: Router) {
    this.offerRequest = new OfferRequest;
   }

  ngOnInit(): void {
  }

  onSubmit(){
    this.offerService.createOffer(this.offerRequest).subscribe(data => {
      console.log(data);
    })
  }

}
