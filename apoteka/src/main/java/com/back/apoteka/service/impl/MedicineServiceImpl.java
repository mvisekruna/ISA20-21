package com.back.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Medicine;
import com.back.apoteka.repository.MedicineRepository;
import com.back.apoteka.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService{

	@Autowired
	MedicineRepository medicineRepository;
	
	@Override
	public Medicine findById(Long id) {
		return medicineRepository.findById(id).orElse(null);
	}

	@Override
	public Medicine findByName(String name) {
		return medicineRepository.findByName(name);
	}

	@Override
	public List<Medicine> findAll() {
		return medicineRepository.findAll();
	}

}
