package com.back.apoteka.request;

import java.util.Date;

import com.back.apoteka.model.OrderStatus;
import com.back.apoteka.model.Pharmacy;

import lombok.Data;

@Data
public class OrderRequest {
	
	private Long orderId;
	
	private Long pharmacyId;
	
	private String dateOfEnd;
	

}
