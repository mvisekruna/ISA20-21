package com.back.apoteka.service.impl;

import java.sql.Timestamp;
import java.util.Date;
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
		System.out.println(pr);
		promo.setPharm(pharmacy);
		promo.setStartPromo(Timestamp.valueOf(pr.getStartPromo()));
		promo.setEndPromo(Timestamp.valueOf(pr.getEndPromo()));
		promo.setDescription(pr.getDescription());
		
		promoRepo.save(promo);
		
		List<PharmacyPromoUser> pharmacyPromoUsers = pharmacyPromoUserService.findAll();
		for(PharmacyPromoUser pharmacyPromoUser : pharmacyPromoUsers) {
			if(pharmacyPromoUser.isSubscribed() && pharmacyPromoUser.getPharmacy().getId().equals(pharmacy.getId())) {
				System.out.println("usao u if");
				//promoRepo.save(promo);
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
	
	public Date parseDate(String dateString) {
		System.out.println(dateString);
		String[] parts = dateString.split("-");
		String[] parts1 = parts[2].split(" ");
		Date date = new Timestamp(0,0,0,0,0,0,0);
		date.setDate(Integer.parseInt(parts1[0]));
		date.setMonth(Integer.parseInt(parts[1]));
		date.setYear(Integer.parseInt(parts[0]) - 1900);
		return date;
	}
	

	@Override
	public Promotion findById(Long id) throws AccessDeniedException {
		Promotion promotion = promoRepo.findById(id).orElseGet(null);
		return promotion;
	}
	
	
	
}
