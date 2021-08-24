package com.back.apoteka.service;

import com.back.apoteka.model.Promotion;
import com.back.apoteka.request.PromotionRequest;

public interface PromotionService {
	
	Promotion findById(Long id);
	Promotion addPromotion(PromotionRequest pr);

}
