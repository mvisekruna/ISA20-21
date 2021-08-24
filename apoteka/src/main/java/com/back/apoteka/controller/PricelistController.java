package com.back.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.Pricelist;
import com.back.apoteka.request.PricelistRequest;
import com.back.apoteka.request.UpdatePricelistRequest;
import com.back.apoteka.service.PricelistService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/pricelist", produces = MediaType.APPLICATION_JSON_VALUE)
public class PricelistController {
	
	@Autowired
	PricelistService pricelistService;
	
	@GetMapping
	public Pricelist findById(@PathVariable Long id) {
		return pricelistService.findById(id);
	}
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public Pricelist save(@RequestBody PricelistRequest pricelistRequest) {
		return pricelistService.save(pricelistRequest);
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public Pricelist updatePricelist(@RequestBody UpdatePricelistRequest pricelistRequest) {
		return pricelistService.updatePricelist(pricelistRequest);
	}
	
	
	
	
	

}
