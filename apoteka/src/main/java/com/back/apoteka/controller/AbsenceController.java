package com.back.apoteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.Absence;
import com.back.apoteka.request.CreateAbsenceRequest;
import com.back.apoteka.service.AbsenceService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/absence")
public class AbsenceController {
	
	@Autowired
	AbsenceService absenceService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole(\"DERMATOLOGIST\",\"PHARMACIST\")")
	public Absence sendRequestForAbsence(@RequestBody CreateAbsenceRequest createAbsenceRequest) {
		return absenceService.sendRequestForAbsence(createAbsenceRequest);
	}

}
