package com.back.apoteka.request;



import lombok.Data;

@Data
public class OrderRequest {
	
	private Long orderId;
	
	private Long pharmacyId;
	
	private String dateOfEnd;
	

}
