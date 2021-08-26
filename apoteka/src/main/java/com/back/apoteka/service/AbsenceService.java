package com.back.apoteka.service;

import java.util.List;

import com.back.apoteka.model.Absence;
import com.back.apoteka.request.CreateAbsenceRequest;

public interface AbsenceService {
	
	Absence sendRequestForAbsence(CreateAbsenceRequest createAbsenceRequest);
	
	List<Absence> findAllRequestsFromPharmacists();
	void absenceApprovalForPharmacist(Long pharmacistId);
	void absenceDeniedForPharmacist(Long pharmacistId);
	
	List<Absence> findAllRequestsFromDermatologists();
	void absenceApprovalForDermatologist(Long dermatologistId);
	void absenceDeniedForDermatologist(Long dermatologistId);
	
	
}
