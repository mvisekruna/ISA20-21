package com.back.apoteka.request;

import lombok.Data;

@Data
public class OfferRequest {

	private Long orderId;
	private double price;
}
