package com.back.apoteka.service;

import java.util.List;

import com.back.apoteka.model.MedicineReservation;
import com.back.apoteka.request.MedicineReservationRequest;

public interface MedicineReservationService {

	List<MedicineReservation> findByPharmacy(Long id);

	boolean save(MedicineReservationRequest mrr);

}
