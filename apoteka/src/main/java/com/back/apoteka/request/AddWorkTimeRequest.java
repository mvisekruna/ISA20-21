package com.back.apoteka.request;

import java.util.Date;

import lombok.Data;

@Data
public class AddWorkTimeRequest {
	
	private String fromWT;
	private String toWT;
	private Long dayWT;
	private String pharmOrDermEmail;
	
	
	private Long pharmacyId;
	
}
