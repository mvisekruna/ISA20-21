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
	
	@GetMapping("/pharmacists")
	public List<Absence> findAllRequestsFromPharmacists() {
		return absenceService.findAllRequestsFromPharmacists();
	}
	
	@GetMapping("/dermatologists")
	public List<Absence> findAllRequestsFromDermatologists() {
		return absenceService.findAllRequestsFromDermatologists();
	}
	
	@PostMapping("/approvedpharmacist/{pharmacistId}")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public void absenceApprovalForPharmacist(@PathVariable Long pharmacistId) {
		absenceService.absenceApprovalForPharmacist(pharmacistId);
	}
	
	@PostMapping("/deniedpharmacist/{pharmacistId}")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public void absenceDeniedForPharmacist(@PathVariable Long pharmacistId) {
		absenceService.absenceDeniedForPharmacist(pharmacistId);
	}
	
	@PostMapping("/approveddermatologist/{dermatologistId}")
	@PreAuthorize("hasRole('SYSTEM_ADMIN')")
	public void absenceApprovalForDermatologist(@PathVariable Long dermatologistId) {
		absenceService.absenceApprovalForDermatologist(dermatologistId);
	}
	
	@PostMapping("/denieddermatologist/{dermatologistId}")
	@PreAuthorize("hasRole('SYSTEM_ADMIN')")
	public void absenceDeniedForDermatologist(@PathVariable Long dermatologistId) {
		absenceService.absenceDeniedForDermatologist(dermatologistId);
	}
	
	

}
