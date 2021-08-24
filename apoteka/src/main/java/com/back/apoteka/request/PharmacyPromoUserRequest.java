package com.back.apoteka.request;

import lombok.Data;

@Data
public class PharmacyPromoUserRequest {

	private Long pharmacyId;
	
	private Long promoUserId;
	
	private boolean isSubscribed;
}
