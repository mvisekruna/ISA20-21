package com.back.apoteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.QuarterReport;
import com.back.apoteka.service.QuarterReportService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/quarterreport")
public class QuarterReportController {
	
	@Autowired
	private QuarterReportService quarterReportService;
		
	@GetMapping("/count")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public QuarterReport countExaminations() {
		return quarterReportService.countExaminations();
	}
	

}
