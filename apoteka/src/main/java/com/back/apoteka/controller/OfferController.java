package com.back.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.Offer;
import com.back.apoteka.request.OfferRequest;
import com.back.apoteka.service.OfferService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/offer")
public class OfferController {
	
	@Autowired
	OfferService offerService;
	
	@PostMapping
	@PreAuthorize("hasRole('SUPPLIER')")
	public Offer createOffer(@RequestBody OfferRequest or) {
		return offerService.save(or);
	}
	
	@GetMapping("/getoffers/{pharmacyId}")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public List<Offer> getOffersForOrder(@PathVariable Long pharmacyId) {
		return offerService.getOffersForOrder(pharmacyId);
	}
	
	@GetMapping("/pickoffer/{offerId}")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public Offer pickOneOffer(@PathVariable Long offerId) {
		return offerService.pickOneOffer(offerId);
	}

}
