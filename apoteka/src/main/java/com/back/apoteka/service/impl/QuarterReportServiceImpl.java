package com.back.apoteka.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Examination;
import com.back.apoteka.model.QuarterReport;
import com.back.apoteka.service.ExaminationService;
import com.back.apoteka.service.QuarterReportService;

@Service
public class QuarterReportServiceImpl implements QuarterReportService {
	
	@Autowired
	ExaminationService examinationService;

	@Override
	public QuarterReport countExaminations() {
		List<Examination> examinations = examinationService.findAll();
		QuarterReport quarterReport = new QuarterReport();
		
		Date currentYear = new Date();
		
		for(Examination e: examinations) {
			if(e.getDateAndTime().getYear()==(currentYear.getYear())) {
				if(e.getDateAndTime().getMonth() == 0 || e.getDateAndTime().getMonth() == 1 || e.getDateAndTime().getMonth() == 2) {
					quarterReport.setFirstQuarter(quarterReport.getFirstQuarter()+1);
				} else if(e.getDateAndTime().getMonth() == 3 || e.getDateAndTime().getMonth() == 4 || e.getDateAndTime().getMonth() == 5) {
					quarterReport.setSecondQuarter(quarterReport.getSecondQuarter()+1);
				} else if(e.getDateAndTime().getMonth() == 6 || e.getDateAndTime().getMonth() == 7 ||e.getDateAndTime().getMonth() == 8) {
					quarterReport.setThirdQuarter(quarterReport.getThirdQuarter()+1);
				} else if(e.getDateAndTime().getMonth() == 9 || e.getDateAndTime().getMonth() == 10 || e.getDateAndTime().getMonth() == 11) {
					quarterReport.setFourthQuarter(quarterReport.getFourthQuarter()+1);
				}
			}
		}
		return quarterReport;
	}

}
