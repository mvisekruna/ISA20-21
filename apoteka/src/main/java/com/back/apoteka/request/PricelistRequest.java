package com.back.apoteka.request;



import lombok.Data;

@Data
public class PricelistRequest {
	
	private Long id;
	private Long medicineId;
	private double price;
	private String fromPeriod;
	private String toPeriod;
	private Long pharmacyId;
}
