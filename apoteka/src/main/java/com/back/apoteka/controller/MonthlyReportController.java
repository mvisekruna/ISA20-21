package com.back.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.MonthlyReport;
import com.back.apoteka.service.MonthlyReportService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/monthlyreport")
public class MonthlyReportController {
	
	@Autowired
	private MonthlyReportService monthlyReportService;

	
	@GetMapping("/count")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public MonthlyReport countExaminations() {
		return monthlyReportService.countExaminations();
	}

}
