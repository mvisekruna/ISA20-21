package com.back.apoteka.response;

import com.back.apoteka.model.Examination;

import lombok.Data;
@Data
public class CanUnscheduleResponce {
	private Examination exam;
	private boolean canUnschedule;	
}
