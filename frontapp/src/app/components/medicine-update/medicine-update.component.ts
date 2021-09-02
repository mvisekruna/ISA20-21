import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Medicine } from 'src/app/model/medicine';
import { MedicineUpdateRequest } from 'src/app/model/medicine-update-request';
import { PharmacyServiceService } from 'src/app/service/pharmacy-service.service';

@Component({
  selector: 'app-medicine-update',
  templateUrl: './medicine-update.component.html',
  styleUrls: ['./medicine-update.component.css']
})
export class MedicineUpdateComponent implements OnInit {

  pharmacyId: any;
  medicineUpdateRequest: MedicineUpdateRequest;
  oldName: any;
  medName: any;
  medicine: Medicine;
  

  constructor(private pharmacyService: PharmacyServiceService, private router: Router) {
    this.pharmacyId = this.router?.getCurrentNavigation()?.extras?.state?.pharmacyId;
    this.oldName = this.router?.getCurrentNavigation()?.extras?.state?.medName;
    this.medName = this.router?.getCurrentNavigation()?.extras?.state?.medName;
    this.medicineUpdateRequest = new MedicineUpdateRequest;
    
  }

  ngOnInit(): void {

  }

  onSubmit() {
    console.log(this.oldName);
    
    this.pharmacyService.updateMedicineFromPharmacy(this.pharmacyId, this.oldName, this.medName).subscribe(data => {
      
      console.log(data);
    })
  }

}
