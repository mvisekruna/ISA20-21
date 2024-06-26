package com.back.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.WorkTime;
import com.back.apoteka.request.AddWorkTimeRequest;
import com.back.apoteka.service.WorkTimeService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/worktime")
public class WorkTimeController {

	@Autowired
	private WorkTimeService wtService;
	
	@GetMapping("/find/{dermatologistId}")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public List<WorkTime> findByDermatologist(@PathVariable Long dermatologistId) {
		return wtService.findByDermatologist(dermatologistId);
	}
	
	@PostMapping("/addworktime")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public WorkTime addWorkTime(@RequestBody AddWorkTimeRequest wtr) {
		return wtService.addWorkTime(wtr);
	}
	
}
