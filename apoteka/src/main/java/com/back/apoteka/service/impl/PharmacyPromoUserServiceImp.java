package com.back.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.PharmacyPromoUser;
import com.back.apoteka.model.User;
import com.back.apoteka.repository.PharmacyPromoUserRepository;
import com.back.apoteka.request.PharmacyPromoUserRequest;
import com.back.apoteka.service.PharmacyPromoUserService;
import com.back.apoteka.service.PharmacyService;
import com.back.apoteka.service.UserService;

@Service
public class PharmacyPromoUserServiceImp implements PharmacyPromoUserService {

	@Autowired
	PharmacyPromoUserRepository ppuRepo;
	
	@Autowired
	CustomUserDetailsService customUserService;
	
	@Autowired
	PharmacyService pharmacyService;
	
	@Autowired
	UserService userService;
	
	
	
	@Override
	public List<PharmacyPromoUser> findAll() {
		return ppuRepo.findAll();
	}
	
	@Override
	public PharmacyPromoUser findById(Long id) throws AccessDeniedException {
		PharmacyPromoUser ppu = ppuRepo.findById(id).orElseGet(null);
		return ppu;
	}
	
	public void subscribe(Long ppuId) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		User patient = (User) customUserService.loadUserByUsername(currentUser.getName());
		
		PharmacyPromoUser ppu = findById(ppuId);
		ppu.setSubscribed(true);
	}
	
	public PharmacyPromoUser save(PharmacyPromoUserRequest pharmacyPromoUserRequest) {
		PharmacyPromoUser pharmacyPromoUser = new PharmacyPromoUser();
		
		Pharmacy pharmacy = pharmacyService.findById(pharmacyPromoUserRequest.getPharmacyId());
		pharmacyPromoUser.setPharmacy(pharmacy);
		
		User promoUser = userService.findById(pharmacyPromoUserRequest.getPromoUserId());
		pharmacyPromoUser.setPromoUser(promoUser);
		
		pharmacyPromoUser.setSubscribed(true);
		
		return ppuRepo.save(pharmacyPromoUser);
	}



}
