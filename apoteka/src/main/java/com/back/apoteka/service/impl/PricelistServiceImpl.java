package com.back.apoteka.service.impl;


import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Medicine;
import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.Pricelist;
import com.back.apoteka.repository.PricelistRepository;
import com.back.apoteka.request.PricelistRequest;
import com.back.apoteka.request.UpdatePricelistRequest;
import com.back.apoteka.service.MedicineService;
import com.back.apoteka.service.PharmacyService;
import com.back.apoteka.service.PricelistService;

@Service
public class PricelistServiceImpl implements PricelistService {

	@Autowired
	PricelistRepository pricelistRepo;
	
	@Autowired
	MedicineService medicineService;
	
	@Autowired
	PharmacyService pharmacyService;
	
	@Override
	public Pricelist findById(Long id) {
		Pricelist p = pricelistRepo.findById(id).orElseGet(null);
		return p;
	}
	
	
	@Override
	public Pricelist save(PricelistRequest pricelistRequest) {
		Pricelist pricelist = new Pricelist();
		
		Medicine medicine = medicineService.findById(pricelistRequest.getMedicineId());
		Pharmacy pharmacy = pharmacyService.findById(pricelistRequest.getPharmacyId());
		
		pricelist.setMedicine(medicine);
		pricelist.setPharmacy(pharmacy);
		pricelist.setPrice(pricelistRequest.getPrice());
		pricelist.setFromPeriod(Timestamp.valueOf(pricelistRequest.getFromPeriod()));
		pricelist.setToPeriod(Timestamp.valueOf(pricelistRequest.getToPeriod()));
				
		return pricelistRepo.save(pricelist);
	}

	@Override
	public Pricelist updatePricelist(UpdatePricelistRequest pricelistRequest) {
		Pricelist p = findById(pricelistRequest.getId());
		
		p.setPrice(pricelistRequest.getPrice());
		p = this.pricelistRepo.save(p);
		
		return null;
	}

}
