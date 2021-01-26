package com.back.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.apoteka.model.MedicineReservation;

public interface MedicineReservationRepository extends JpaRepository<MedicineReservation, Long>{

}
