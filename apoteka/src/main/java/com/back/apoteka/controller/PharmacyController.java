package com.back.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.Medicine;
import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.User;
import com.back.apoteka.request.AddPhamracyAdminReuest;
import com.back.apoteka.request.AddPharmacyRequest;
import com.back.apoteka.request.AddWorkTimeRequest;
import com.back.apoteka.request.MedicineUpdateRequest;
import com.back.apoteka.service.impl.PharmacyServiceImpl;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/pharmacy")
public class PharmacyController {

	@Autowired
	PharmacyServiceImpl pharmacyService;
	
	@GetMapping()
	public List<Pharmacy> findAll(){ //radi
		return pharmacyService.findAll();
	}
	
	@GetMapping("/id")
	public Pharmacy findById(@RequestBody int id){
		return pharmacyService.findById(Long.valueOf(id));
	}
	
	@PostMapping("/name")//hasRole patient
	public Pharmacy findByName(@RequestBody String pharmacyName){
		return pharmacyService.findByName(pharmacyName);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('SYSTEM_ADMIN')")
	public Pharmacy addPharmacy(@RequestBody AddPharmacyRequest apr) {
		return pharmacyService.addPharmacy(apr);
	}
	
	@PostMapping("/setadmin")
	@PreAuthorize("hasRole('SYSTEM_ADMIN')")
	public Pharmacy addPharmacyAdmin(@RequestBody AddPhamracyAdminReuest apar) {
		return pharmacyService.addPharmacyAdmin(apar);
	}
	
	@GetMapping("/havemedicine/{id}")
	public List<Pharmacy> getPharmacyWithMedicine(@PathVariable int id){ //radi
		return pharmacyService.getPharmacyWithMedicine(id);
	}
	
/**LEKOVI***********************************************/

	@GetMapping("/getMedicines/{id}")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public List<Medicine> getMedicinesFromPharmacy(@PathVariable Long id){ //radi
		return pharmacyService.getMedicinesFromPharmacy(id);
	}
	
	@GetMapping("/getAllPharmMedicines/{id}/{medName}") 
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public List<Medicine> getAllMedicineFromPharmacy(@PathVariable Long id, @PathVariable String medName){ //radi
		return pharmacyService.getAllMedicinesFromPharmacy(id, medName);
	}
	
	@GetMapping("/getOnePharmMedicine/{id}/{medName}") 
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")	
	public Medicine findOneMedicineFromPharmacy(@PathVariable Long id, @PathVariable String medName) { //radi
		return pharmacyService.findOneMedicineFromPharmacy(id, medName);
	}
	
	@PostMapping("/updatePharmMedicine/{id}/{medName}") 
	@PreAuthorize("hasRole('PHARMACY_ADMIN')") //radi
	public Medicine updateMedicineFromPharmacy(@PathVariable Long id, @PathVariable String medName, @RequestBody MedicineUpdateRequest mur) {
		return pharmacyService.updateMedicineFromPharmacy(id, medName, mur);
	}
	
	@PostMapping("/deletePharmMedicine/{id}/{medName}") 
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public String deleteMedicineFromPharmacy(@PathVariable Long id, @PathVariable String medName) {
		 return pharmacyService.deleteMedicineFromPharmacy(id, medName);	
	}

/*FARMACEUTI******************************************/
	
	@PostMapping("/setpharmacist")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')") //radi
	public Pharmacy addPharmacyPharmacist(@RequestBody AddWorkTimeRequest wtr) {
		return pharmacyService.addPharmacyPharmacist(wtr);
	}
	
	@GetMapping("/getPharmacists/{id}") 
	@PreAuthorize("hasRole('PHARMACY_ADMIN')") //radi
	public List<User> getPharmacists(@PathVariable Long id){
		return pharmacyService.getPharmacists(id);
	}
	
	@GetMapping("/getAllPharmPharmacists/{id}/{pharmacistEmail}") ///kako ruta
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public List<User> getAllPharmacistsFromPharmacy(@PathVariable Long id, @PathVariable String pharmacistEmail){
		return pharmacyService.getAllPharmacistsFromPharmacy(id, pharmacistEmail);
	}
	
	@GetMapping("/getOnePharmPharmacist/{id}/{pharmacistSurname}") ///kako ruta
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public User findOnePharmacistFromPharmacy(@PathVariable Long id,@PathVariable String pharmacistSurname) {
		return pharmacyService.findOnePharmacistFromPharmacy(id, pharmacistSurname);
	}
	
	@PostMapping("/deletePharmPharmacist/{id}/{pharmacistSurname}") //kako ruta
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public String deletePharmacistFromPharmacy(@PathVariable Long id,@PathVariable String pharmacistSurname) {
		return pharmacyService.deletePharmacistFromPharmacy(id, pharmacistSurname);
		
	}
	
/*DERMATOLOZI***************************************************/
	
	@PostMapping("/setdermatologist")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public Pharmacy addPharmacyDerm(@RequestBody AddWorkTimeRequest wtr) {
		return pharmacyService.addPharmacyDerm(wtr);
	}
	
	@GetMapping("/getDermatologists/{id}") 
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public List<User> getDermatologists(@PathVariable Long id) {
		return pharmacyService.getDermatologists(id);
	}
	
	@GetMapping("/getAllPharmDermatologists/{id}/{dermEmail}") ///kako ruta
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public List<User> getAllDermatologistsFromPharmacy(@PathVariable Long id,@PathVariable String dermEmail){
		return pharmacyService.getAllDermatologistsFromPharmacy(id, dermEmail);
	}
	
	@GetMapping("/getOnePharmDermatologist/{id}/{dermatologistSurname}") ///kako ruta
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public User findOneDermatologistFromPharmacy(@PathVariable Long id,@PathVariable String dermatologistSurname) {
		return pharmacyService.findOneDermatologistFromPharmacy(id, dermatologistSurname);
	}
	
	@PostMapping("/deletePharmDermatologist/{id}/{dermatologistSurname}") //kako ruta
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public String deleteDermatologistFromPharmacy(@PathVariable Long id, @PathVariable String dermatologistSurname) {
		return pharmacyService.deleteDermatologistFromPharmacy(id, dermatologistSurname);
		
	}
}
