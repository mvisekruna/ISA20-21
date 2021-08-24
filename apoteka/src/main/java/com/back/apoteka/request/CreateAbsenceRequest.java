package com.back.apoteka.request;

import java.util.Date;

import com.back.apoteka.model.User;

import lombok.Data;

@Data
public class CreateAbsenceRequest {
	
	//private Long id;
	
	private Date startAbsence;
	
	private Date endAbsence;
	
	//private User pharmacist;
	
	//private User dermatologist;

}
