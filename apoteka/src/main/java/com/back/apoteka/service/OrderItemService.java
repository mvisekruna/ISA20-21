package com.back.apoteka.service;

import com.back.apoteka.model.OrderItem;
import com.back.apoteka.request.AddOrderItemRequest;

public interface OrderItemService {
	
	OrderItem save(AddOrderItemRequest oir);
	

}
