package com.back.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.Examination;
import com.back.apoteka.service.impl.ExaminationServiceImpl;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/examination")
public class ExaminationController {
	@Autowired
	ExaminationServiceImpl examinationService;
	
	@PostMapping("/pharm")
	public List<Examination> findByPharmacy(@RequestBody int id){
		System.out.println(id);
		return examinationService.findByPharmacy(Long.valueOf(id));
	}

	@PostMapping("/derm")
	public List<Examination> findByDermatologist(@RequestBody int id){
		System.out.println(id);
		return examinationService.findByDermatologist(Long.valueOf(id));
	}
}
