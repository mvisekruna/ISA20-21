package com.back.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.User;
import com.back.apoteka.repository.PharmacyRepository;
import com.back.apoteka.service.PharmacyService;

@Service
public class PharmacyServiceImpl implements PharmacyService{

	@Autowired
	PharmacyRepository pharmacyRepository;
	
	@Override
	public Pharmacy findById(Long id) {
		System.out.println("usao u pharmacyservce");
		System.out.println(pharmacyRepository.findById(id));
		return pharmacyRepository.findById(id).orElse(null);
	}

	@Override
	public Pharmacy findByName(String name) {
		return pharmacyRepository.findByName(name);
	}

	@Override
	public List<Pharmacy> findAll() {
		System.out.println("usao u pharmacyservce");
		return pharmacyRepository.findAll();
	}

	@Override
	public List<User> getPharmacists(Long id) {
		Pharmacy p=findById(id);
		List<User> pharmacists= p.getPharmacists();
		return pharmacists;
	}

}
