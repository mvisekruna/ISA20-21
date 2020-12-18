package com.back.apoteka.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.Medicine;
import com.back.apoteka.service.impl.MedicineServiceImpl;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/medicine")
public class MedicineController {

	@Autowired
	MedicineServiceImpl medicineService;
	
	@GetMapping()
	public List<Medicine> findAll(){
		return medicineService.findAll();
	}
	
	@GetMapping("/id")
	public Medicine findById(@RequestBody int id){
		return medicineService.findById(Long.valueOf(id));
	}
	
	@GetMapping("/name")
	public Medicine findByName(@RequestBody String name){
		return medicineService.findByName(name);
	}
	
}
