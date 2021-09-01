package com.back.apoteka.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Examination;
import com.back.apoteka.model.MonthlyReport;
import com.back.apoteka.service.ExaminationService;
import com.back.apoteka.service.MonthlyReportService;

@Service
public class MonthlyReportServiceImpl  implements MonthlyReportService {
	
	@Autowired
	ExaminationService examinationService;
	
	
	@Override
	public MonthlyReport countExaminations() {
		
		List<Examination> examinations = examinationService.findAll();
		MonthlyReport monthlyReport = new MonthlyReport();
		
		Date currentYear = new Date();
	
		
		for(Examination e: examinations) {
			if(e.getDateAndTime().getYear()==(currentYear.getYear())) {
				if(e.getDateAndTime().getMonth() == 0) {
					monthlyReport.setJanuary(monthlyReport.getJanuary()+1);
				} else if(e.getDateAndTime().getMonth() == 1) {
					monthlyReport.setFebruary(monthlyReport.getFebruary()+1);
				} else if(e.getDateAndTime().getMonth() == 2) {
					monthlyReport.setMarch(monthlyReport.getMarch()+1);
				} else if(e.getDateAndTime().getMonth() == 3) {
					monthlyReport.setApril(monthlyReport.getApril()+1);
				} else if(e.getDateAndTime().getMonth() == 4) {
					monthlyReport.setMay(monthlyReport.getMay()+1);
				} else if(e.getDateAndTime().getMonth() == 5) {
					monthlyReport.setJune(monthlyReport.getJune()+1);
				} else if(e.getDateAndTime().getMonth() == 6) {
					monthlyReport.setJuly(monthlyReport.getJuly()+1);
				} else if(e.getDateAndTime().getMonth() == 7) {
					monthlyReport.setAugust(monthlyReport.getAugust()+1);
				} else if(e.getDateAndTime().getMonth() == 8) {
					monthlyReport.setSeptember(monthlyReport.getSeptember()+1);
				} else if(e.getDateAndTime().getMonth() == 9) {
					monthlyReport.setOctober(monthlyReport.getOctober()+1);
				} else if(e.getDateAndTime().getMonth() == 10) {
					monthlyReport.setNovember(monthlyReport.getNovember()+1);
				} else if (e.getDateAndTime().getMonth() == 11) {
					monthlyReport.setDecember(monthlyReport.getDecember()+1);
				} 
			}
		}
		return monthlyReport;
	}

}
