package com.back.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.apoteka.model.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long>{
	

}
