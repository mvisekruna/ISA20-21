package com.back.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.PharmacyPromoUser;
import com.back.apoteka.request.PharmacyPromoUserRequest;
import com.back.apoteka.service.impl.PharmacyPromoUserServiceImp;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/pharmacypromouser")
public class PharmacyPromoUserController {
	
	@Autowired
	PharmacyPromoUserServiceImp pharmacyPromoUserService;
	
	
	@GetMapping
	public List<PharmacyPromoUser> findAll() {
		return pharmacyPromoUserService.findAll();
	}
	
	@GetMapping("/{id}")
	public PharmacyPromoUser findById(@PathVariable Long id) throws AccessDeniedException {
		return pharmacyPromoUserService.findById(id);
	}

	@GetMapping("/subscribe/{ppuId}")
	@PreAuthorize("hasRole('PATIENT')")
	public void subscribe(@PathVariable Long ppuId) {
		pharmacyPromoUserService.subscribe(ppuId);
	}

	@PostMapping("/save")
	public PharmacyPromoUser save(@RequestBody PharmacyPromoUserRequest pharmacyPromoUserRequest) {
		return pharmacyPromoUserService.save(pharmacyPromoUserRequest);
	}
	
	
	
}
