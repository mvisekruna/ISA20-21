package com.back.apoteka.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.PharmacyPromoUser;
import com.back.apoteka.model.Promotion;
import com.back.apoteka.model.User;
import com.back.apoteka.repository.PromotionRepository;
import com.back.apoteka.request.PromotionRequest;
import com.back.apoteka.service.PromotionService;


import com.back.apoteka.service.PharmacyPromoUserService;
import com.back.apoteka.service.PharmacyService;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	PromotionRepository promoRepo;
	
	@Autowired
	PharmacyPromoUserService pharmacyPromoUserService;
	
	@Autowired
	EmailServiceImpl emailService;
	
	@Autowired
	PharmacyService pharmacyService;
	
	
	@Override
	public Promotion addPromotion(PromotionRequest pr) {
		Pharmacy pharmacy = pharmacyService.findById(pr.getPharmId());
		Promotion promo = new Promotion();
		promo.setPharm(pharmacy);
		promo.setStartPromo(pr.getStartPromo());
		promo.setEndPromo(pr.getEndPromo());
		promo.setDescription(pr.getDescription());
		
		List<PharmacyPromoUser> pharmacyPromoUsers = pharmacyPromoUserService.findAll();
		for(PharmacyPromoUser pharmacyPromoUser : pharmacyPromoUsers) {
			if(pharmacyPromoUser.isSubscribed() && pharmacyPromoUser.getPharmacy().getId().equals(pharmacy.getId())) {
				System.out.println("usao u if");
				promoRepo.save(promo);
				User user = pharmacyPromoUser.getPromoUser();
				try {
					emailService.sendNewPromotionNotification(user.getEmail(), promo.getId());
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
		return promo;	
	}

	@Override
	public Promotion findById(Long id) throws AccessDeniedException {
		Promotion promotion = promoRepo.findById(id).orElseGet(null);
		return promotion;
	}
	
	
	
}
