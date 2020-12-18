package com.back.apoteka.service;

import java.util.List;

import com.back.apoteka.model.Pharmacy;

public interface PharmacyService {

	Pharmacy findById(Long id);
	Pharmacy findByName(String name);
	List<Pharmacy> findAll();
}
