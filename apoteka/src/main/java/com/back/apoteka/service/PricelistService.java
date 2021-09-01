package com.back.apoteka.service;

import java.util.List;

import com.back.apoteka.model.Pricelist;
import com.back.apoteka.request.PricelistRequest;
import com.back.apoteka.request.UpdatePricelistRequest;

public interface PricelistService {
	
	List<Pricelist> findAllFromOnePharmacy();
	
	Pricelist findById(Long id);
	
	Pricelist save(PricelistRequest pricelistRequest);

	Pricelist updatePricelist(UpdatePricelistRequest pricelistRequest);
}
