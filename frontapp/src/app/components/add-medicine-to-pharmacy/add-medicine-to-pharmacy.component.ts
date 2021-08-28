import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddMedicineToPharmacyRequest } from 'src/app/model/add-medicine-to-pharmacy-request';
import { PharmacyServiceService } from 'src/app/service/pharmacy-service.service';

@Component({
  selector: 'app-add-medicine-to-pharmacy',
  templateUrl: './add-medicine-to-pharmacy.component.html',
  styleUrls: ['./add-medicine-to-pharmacy.component.css']
})
export class AddMedicineToPharmacyComponent implements OnInit {

  addMedicineToPharmacyRequest: AddMedicineToPharmacyRequest;
  
  constructor(private pharmacyService: PharmacyServiceService, private router: Router) {
    this.addMedicineToPharmacyRequest = new AddMedicineToPharmacyRequest;
   }

  ngOnInit(): void {
  }

  onSubmit(){
    this.pharmacyService.addMedicineToPharmacy(this.addMedicineToPharmacyRequest).subscribe(data => {
      console.log(data);
    })
  }

}
