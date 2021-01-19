package com.back.apoteka.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.back.apoteka.model.Examination;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {

	//List<Examination> findAllByPharmacy_id(Long id);
}
