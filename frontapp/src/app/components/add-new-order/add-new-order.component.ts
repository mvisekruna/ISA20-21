import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderRequest } from 'src/app/model/order-request';
import { OrderServiceService } from 'src/app/service/order-service.service';

@Component({
  selector: 'app-add-new-order',
  templateUrl: './add-new-order.component.html',
  styleUrls: ['./add-new-order.component.css']
})
export class AddNewOrderComponent implements OnInit {

  orderRequest: OrderRequest;
  filterDate: Date;

  constructor(private orderService: OrderServiceService, private router: Router) { }

  ngOnInit(): void {
    this.orderRequest = new OrderRequest;
    this.filterDate = new Date();
  }

  onSubmit(){
    console.log(this.orderRequest)
    this.filterDate = new Date((<HTMLInputElement>document.getElementById("dateOfEnd")).value);
    this.orderRequest.dateOfEnd = this.filterDate.toISOString().slice(0, 16).replace('T', ' ')+":00";


    this.orderService.saveOrder(this.orderRequest).subscribe( data => {
      console.log(data);
      //this.router.navigate(["/homepage"]);
    });
  }

  get dateOfEnd(): string {
    return this.filterDate.toISOString().slice(0, 16).replace('T', ' ')+":00";
  }

  set dateOfEnd(value: string) {
    this.filterDate = new Date(value);
  }

}
