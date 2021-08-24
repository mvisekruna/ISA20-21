package com.back.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.back.apoteka.model.Absence;
import com.back.apoteka.model.Pharmacy;


public interface AbsenceRepository extends JpaRepository<Absence, Long>{

	@Query(value = "FROM pharmacy_pharmacist p where p.pharmacists_id:=pharmacistId", nativeQuery = true)
	Pharmacy findPharmacistPharmacy(Long pharmacistId);
	
}
