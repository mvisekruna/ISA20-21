package com.back.apoteka.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.Examination;
import com.back.apoteka.model.Medicine;
import com.back.apoteka.model.MedicineReservation;
import com.back.apoteka.request.MedicineRequest;
import com.back.apoteka.request.MedicineReservationRequest;
import com.back.apoteka.request.MedicineUpdateRequest;
import com.back.apoteka.request.ScheduleExaminationRequest;
import com.back.apoteka.response.CanCancelReservationResponce;
import com.back.apoteka.service.impl.MedicineReservationServiceImpl;
import com.back.apoteka.service.impl.MedicineServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/medicine", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineController {

	@Autowired
	MedicineServiceImpl medicineService;
	
	@GetMapping("/all")
	//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public List<Medicine> findAll(){
		return this.medicineService.findAll();
	}
	
	@GetMapping("getId/{id}") //u postmanu saljem getId/1 na primer, bez zagrada
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public Medicine findById(@PathVariable int id){
		return this.medicineService.findById(Integer.toUnsignedLong(id));
	}
	
	@GetMapping("/name")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public Medicine findByName(@RequestBody String name){
		return medicineService.findByName(name);
	}
	
	@PostMapping("/savemedicine")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public Medicine addMedicine(@RequestBody MedicineRequest medRequest) {
		return this.medicineService.saveMed(medRequest);
	}
	
	@PostMapping("/updatemedicine")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public Medicine updateMedicine(Long id, @RequestBody MedicineUpdateRequest mur) {
		return this.medicineService.updateMed(id, mur);
	}
	
	@PostMapping("deletemedicine/{id}")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<Object> deleteMedicine(@PathVariable Long id) {
		Medicine m = medicineService.findById(id);
		if(m == null) {
			return ResponseEntity.notFound().build();
		}
		medicineService.deleteMed(m);
		System.out.println("izbrisao lek");
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT); //code 204 izadje, proveriti u tabele kako se izbrisao
	}
	@Autowired
	MedicineReservationServiceImpl medicineReservationService;
	@PostMapping("/reservation")
	@PreAuthorize("hasRole('PATIENT')")
	public boolean makeReservation(@RequestBody MedicineReservationRequest mrr) {
		return medicineReservationService.save(mrr);
	}
	@GetMapping("/reservation")
	@PreAuthorize("hasRole('PATIENT')")
	public List<CanCancelReservationResponce> getReservations(){
		return medicineReservationService.getReservations();
	}
	@PostMapping("/cancelreservation")
	public boolean cancelREservation(@RequestBody ScheduleExaminationRequest schedule) {
		System.out.println("usao u cancel reserv");
		return medicineReservationService.cancel(schedule);
	}

	
	
}
