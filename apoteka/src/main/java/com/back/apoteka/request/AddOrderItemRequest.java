package com.back.apoteka.request;

import lombok.Data;

@Data
public class AddOrderItemRequest {
	
	private Long medicineId;
	
	private int amount;
	
	private Long orderId;
}
