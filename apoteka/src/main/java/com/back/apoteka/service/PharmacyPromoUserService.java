package com.back.apoteka.service;

import java.util.List;

import com.back.apoteka.model.PharmacyPromoUser;

public interface PharmacyPromoUserService {
	
	PharmacyPromoUser findById(Long id);
	List<PharmacyPromoUser> findAll();

}
