import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Offer } from 'src/app/model/offer';
import { OfferServiceService } from 'src/app/service/offer-service.service';

@Component({
  selector: 'app-offer-list',
  templateUrl: './offer-list.component.html',
  styleUrls: ['./offer-list.component.css']
})
export class OfferListComponent implements OnInit {

  pharmacyId: any;
  offers: Offer[];
  offer: Offer;
  

  constructor(private offerService: OfferServiceService, private router: Router) {
    this.pharmacyId = this.router?.getCurrentNavigation()?.extras?.state?.pharmacyId;
    this.offer = new Offer;
  }

  ngOnInit(): void {
    this.offerService.getOffersForOrder(this.pharmacyId).subscribe(data => {
      this.offers = data;
    })
  }

  onSubmit(offerId: any) {
    this.offer.id = offerId;
    console.log(offerId)
    this.offerService.pickOneOffer(this.offer.id).subscribe(data => {
      console.log(data);
    })
  }

}
