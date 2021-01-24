package com.back.apoteka.request;

import java.sql.Timestamp;

import com.back.apoteka.model.User;

import lombok.Data;

@Data
public class ScheduleCounselingRequest {

	private String dateAndTime;
	private Long pharmacist;
	private Long pharmacyId;
}
