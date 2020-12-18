package com.back.apoteka.service;



import java.util.List;

import com.back.apoteka.model.Medicine;

public interface MedicineService {

	Medicine findById(Long id);
	Medicine findByName(String name);
	List<Medicine> findAll();
}
