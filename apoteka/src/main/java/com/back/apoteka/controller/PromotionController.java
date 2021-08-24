package com.back.apoteka.controller;

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

import com.back.apoteka.model.Promotion;
import com.back.apoteka.request.PromotionRequest;
import com.back.apoteka.service.PromotionService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/promotion")
public class PromotionController {
	
	
	@Autowired
	PromotionService promotionService;

	@GetMapping("/getOne/{promotionId}")
	public Promotion findById(@PathVariable Long promotionId) throws AccessDeniedException {
		return promotionService.findById(promotionId);
	}
	
	@PostMapping("/addPromotion")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public Promotion addPromotion(@RequestBody PromotionRequest pr) {
		System.out.println(pr);
		return promotionService.addPromotion(pr);
	}
	
}
