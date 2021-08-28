package com.back.apoteka.request;

import java.util.Date;

import lombok.Data;

@Data
public class PromotionRequest {
	
	//private Long id;
	
	private String startPromo;
	
	private String endPromo;
	
	private String description;
	
	private Long pharmId;

}
