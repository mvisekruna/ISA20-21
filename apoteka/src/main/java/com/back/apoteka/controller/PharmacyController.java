package com.back.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.service.impl.PharmacyServiceImpl;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/pharmacy")
public class PharmacyController {

	@Autowired
	PharmacyServiceImpl pharmacyService;
	
	@GetMapping()
	public List<Pharmacy> findAll(){
		return pharmacyService.findAll();
	}
	
	@GetMapping("/id")
	public Pharmacy findById(@RequestBody int id){
		return pharmacyService.findById(Long.valueOf(id));
	}
	
	@PostMapping("/name")
	public Pharmacy findByName(@RequestBody String pharmacyName){
		System.out.println(pharmacyName);
		return pharmacyService.findByName(pharmacyName);
	}
}
