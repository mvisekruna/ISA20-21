package com.back.apoteka.request;

import java.sql.Timestamp;
import java.util.Date;

import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.User;

import lombok.Data;
// request pri kreiranju unapred definisanih termina pregleda od strane admina apoteke
@Data
public class ExaminationRequest {
	
	private Date dateAndTime;
	
	private Long dermatologistId;
	
	private Long pharmacyId;
	
	private Long duration;
    	
	private double price;
}
