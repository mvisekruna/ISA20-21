package com.back.apoteka.request;

import java.util.Date;

import lombok.Data;

@Data
public class PricelistRequest {
	
	private Long id;
	private Long medicineId;
	private double price;
	private Date fromPeriod;
	private Date toPeriod;
}
