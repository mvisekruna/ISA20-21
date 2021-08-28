import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddOrderItemRequest } from 'src/app/model/add-order-item-request';
import { OrderItemService } from 'src/app/service/order-item.service';

@Component({
  selector: 'app-add-order-item',
  templateUrl: './add-order-item.component.html',
  styleUrls: ['./add-order-item.component.css']
})
export class AddOrderItemComponent implements OnInit {

  addOrderItemRequest: AddOrderItemRequest;

  constructor(private orderItemService: OrderItemService, private router: Router) {
    this.addOrderItemRequest = new AddOrderItemRequest;
   }

  ngOnInit(): void {
  }

  onSubmit(){
    this.orderItemService.createOrderItem(this.addOrderItemRequest).subscribe(data => {
      console.log(data);
    })
  }

}
