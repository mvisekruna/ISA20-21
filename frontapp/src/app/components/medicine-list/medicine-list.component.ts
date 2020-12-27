import { Component, OnInit } from '@angular/core';
import { MedicineServiceService } from 'src/app/service/medicine-service.service';
import { Medicine } from 'src/app/model/medicine';

@Component({
  selector: 'app-medicine-list',
  templateUrl: './medicine-list.component.html',
  styleUrls: ['./medicine-list.component.css']
})
export class MedicineListComponent implements OnInit {

  title: string;
  medicines: Medicine[];
  constructor(private medicineService: MedicineServiceService) {
    this.title='Medicine list';
   }

  ngOnInit(): void {
    this.medicineService.findAll().subscribe(data => {
      this.medicines=data;
    });
  }


}
