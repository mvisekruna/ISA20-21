package com.back.apoteka.service;

import java.util.List;

import com.back.apoteka.model.Offer;
import com.back.apoteka.request.OfferRequest;

public interface OfferService {
	
	Offer save(OfferRequest or);
	List<Offer> getOffersForOrder(Long pharmacyId);
	Offer pickOneOffer(Long offerId);
	void checkOffersEndDate(Long pharmacyId);
}
