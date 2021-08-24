package com.back.apoteka.request;

import lombok.Data;

@Data
public class UpdatePricelistRequest {
	
	Long id;
	
	private double price;

}
