package com.back.apoteka.service;

import com.back.apoteka.model.Absence;
import com.back.apoteka.request.CreateAbsenceRequest;

public interface AbsenceService {
	
	Absence sendRequestForAbsence(CreateAbsenceRequest createAbsenceRequest);

}
