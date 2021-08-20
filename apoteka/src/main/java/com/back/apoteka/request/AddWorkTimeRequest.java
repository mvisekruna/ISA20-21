package com.back.apoteka.request;

import java.util.Date;

import lombok.Data;

@Data
public class AddWorkTimeRequest {
	
	private Date fromWT;
	private Date toWT;
	private Long dayWT;
	private String pharmOdDermEmail;
	
	
	private Long pharmacyId;
	
}
