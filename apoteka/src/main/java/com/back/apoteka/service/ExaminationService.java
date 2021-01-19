package com.back.apoteka.service;

import java.util.List;

import com.back.apoteka.model.Examination;

public interface ExaminationService {

	List<Examination> findByPharmacy(Long id);

	List<Examination> findByDermatologist(Long id);
}
