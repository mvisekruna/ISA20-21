package com.back.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.back.apoteka.model.Counseling;
import com.back.apoteka.model.Examination;
import com.back.apoteka.model.Medicine;
import com.back.apoteka.model.MedicineReservation;
import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.User;
import com.back.apoteka.model.WorkTime;
import com.back.apoteka.repository.CounselingRepository;
import com.back.apoteka.repository.ExaminationRepository;
import com.back.apoteka.repository.MedicineRepository;
import com.back.apoteka.repository.MedicineReservationRepository;
import com.back.apoteka.repository.PharmacyRepository;
import com.back.apoteka.request.AddMedicineToPharmacyRequest;
import com.back.apoteka.request.AddPhamracyAdminReuest;
import com.back.apoteka.request.AddPharmacyRequest;
import com.back.apoteka.request.AddWorkTimeRequest;
import com.back.apoteka.request.MedicineUpdateRequest;
import com.back.apoteka.service.PharmacyService;
import com.back.apoteka.service.WorkTimeService;

@Service
public class PharmacyServiceImpl implements PharmacyService{

	@Autowired
	PharmacyRepository pharmacyRepository;
	
	@Autowired
	PharmacyService pharmacyService;
	
	@Autowired
	MedicineServiceImpl medicineService;
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	MedicineRepository medicineRepository;
	
	@Autowired
	MedicineReservationRepository medicineReservationRepo;
	
	@Autowired
	CounselingRepository counselingRepo;
	
	@Autowired
	ExaminationRepository examRepo;
	
	@Autowired
	CustomUserDetailsService customUserService;
	
	@Autowired
	WorkTimeService workTimeService;
	
	
	
	@Override
	public List<Pharmacy> findAll() { //radi
		
		User user = userService.getCurrent();
		System.out.println(user);
		if(user == null)
		{
			return pharmacyRepository.findAll();
		}
		System.out.println(user.getAuthority().getName());
		
		if(user.getAuthority().getName().equals("ROLE_PHARMACY_ADMIN")) {
			System.out.println(user.getAuthority().getName());
			List<Pharmacy> pharms = pharmacyRepository.findAll();
			List<Pharmacy> list = new ArrayList<Pharmacy>();
			for(Pharmacy p: pharms) {
				if(p.isUserPharmacyAdmin(user.getId())) {
					list.add(p);
				}
			}
			return list;
		}
		return pharmacyRepository.findAll();
	}
	
	@Override
	public Pharmacy findById(Long id) { //nisam dirala
		System.out.println("usao u pharmacyservce");
		System.out.println(pharmacyRepository.findById(id));
		return pharmacyRepository.findById(id).orElse(null);
	}

	@Override
	public Pharmacy findByName(String name) { //nisam dirala
		return pharmacyRepository.findByName(name);
	}

	public Pharmacy addPharmacy(AddPharmacyRequest apr) { //nisam dirala
		Pharmacy pharm = new Pharmacy();
		pharm.setAddress(apr.getAddress());
		pharm.setDescription(apr.getDescription());
		pharm.setName(apr.getName());
		pharm.setPhone(apr.getPhone());
		return pharmacyRepository.save(pharm);
	}
	
	public Pharmacy addPharmacyAdmin(AddPhamracyAdminReuest apar) { //nisam dirala
		Pharmacy pharm = pharmacyRepository.findById(apar.getPharmacyId()).orElse(null);
		User pharmAdmin = userService.findByEmail(apar.getAdminEmail());
		pharm.getAdminApoteke().add(pharmAdmin);
		return pharmacyRepository.save(pharm);
	}

	public List<Pharmacy> getPharmacyWithMedicine(int id) { //radi
		Medicine m = medicineService.findById(Long.valueOf(id));
		List<Pharmacy> phar = new ArrayList<Pharmacy>();
		List<Pharmacy> temp = findAll();	
		for(Pharmacy p : temp) {
			List<Medicine> meds = p.getMedicines();
			for (Medicine me: meds) {
				if (me.equals(m)) {
					phar.add(p);
				}
			}
		}
		return phar;
	}
	
	@Override
	public Pharmacy findPharmacyByAdmin(String pharmacyAdminEmail) {
		List<Pharmacy> pharmacies = pharmacyService.findAll();
		Pharmacy pharmacy = null;
		for(Pharmacy p: pharmacies) {
			List<User> pharmacists = p.getPharmacists();
			for(User u:pharmacists) {
				if(u.getEmail().equals(pharmacyAdminEmail)) {
					pharmacy = p;
				}
			}
		}
		return pharmacy;
	}

/*LEKOVI***********************************************/
	
	public Pharmacy addMedicineToPharmacy(AddMedicineToPharmacyRequest addMedToPharmacyRequest) {
		Pharmacy pharmacy = pharmacyRepository.findById(addMedToPharmacyRequest.getPharmacyId()).orElse(null);
		Medicine medicinePharmacy = medicineService.findById(addMedToPharmacyRequest.getMedicineId());
		pharmacy.getMedicines().add(medicinePharmacy);
		return pharmacyRepository.save(pharmacy);
	}
	
	
	public List<Medicine> getMedicinesFromPharmacy(Long id){//radi
		User user = userService.getCurrent();
		Pharmacy pharm = findById(id);
		if(pharm.isUserPharmacyAdmin(user.getId())) {
			return pharm.getMedicines();
		} else {
			System.out.println("Nije pharmacy admin te apoteke");
			List<Medicine> list = new ArrayList<Medicine>();
			return list;
		}
	}
	
	public List<Medicine> getAllMedicinesFromPharmacy(Long id, String medName) { //radi
		Pharmacy pharm = findById(id);
		System.out.println(medName);
		List<Medicine> meds = pharm.getMedicines();
		List<Medicine> temp = new ArrayList<Medicine>();
		User user = userService.getCurrent();
		if(pharm.isUserPharmacyAdmin(user.getId())) {
			System.out.println("usao");
			for(Medicine m: meds) {
				if(m.getName().toLowerCase().contains(medName.toLowerCase())) { 
					System.out.println(medName);
					temp.add(m);
				}	
			}
			return temp;
		} else {
			System.out.println("Nije pharmacy admin te apoteke");
			List<Medicine> list = new ArrayList<Medicine>();
			return list;
		}
	}
	
	public Medicine findOneMedicineFromPharmacy(Long id, String medName) { //radi
		Pharmacy pharm = findById(id);
		List<Medicine> meds = pharm.getMedicines();
		Medicine med = null;
		User user = userService.getCurrent();
		if(pharm.isUserPharmacyAdmin(user.getId())) {
			for(Medicine m: meds) {
				if(m.getName().toLowerCase().equals(medName.toLowerCase())) {
					med = m;
				}	
			}
		return med;
		} else {
			System.out.println("Nije pharmacy admin te apoteke");
			return null;
		}
	}
	
	public Medicine updateMedicineFromPharmacy(Long id, String medName, MedicineUpdateRequest mur) { //ubaciti proveru za pharmacyAdmin
		Medicine m = findOneMedicineFromPharmacy(id, medName);//imam
		
		if (m == null) {
			return null;
		}
		
		m.setName(mur.getName());
		m = this.medicineRepository.save(m);
		return m;
	}
	
//dodati proveru je l admin apoteke
	public String deleteMedicineFromPharmacy(Long id, String medName) { //radi
		Medicine med = findOneMedicineFromPharmacy(id, medName);
		System.out.println(med);
		List<MedicineReservation> medsRes = medicineReservationRepo.findAll();
		for(MedicineReservation m: medsRes) {
			System.out.println("usao");
			if(m.getMedicine().getId().equals(med.getId())) {
				System.out.println("usao i u ovo");
				return "Ne moze se izbrisati jer je u rezervacijama";
			} else {
				Pharmacy p = pharmacyService.findById(id);
				List<Medicine> meds = p.getMedicines();
				meds.remove(med);
				p = pharmacyRepository.save(p);
					
			}
		}	
		return "ok";
	}
	
/*FARMACEUTI******************************************/
	
	public Pharmacy addPharmacyPharmacist(AddWorkTimeRequest wtr) { //RADI
		Pharmacy pharmacy = pharmacyRepository.findById(wtr.getPharmacyId()).orElse(null);
		User pharmacistPharmacy = userService.findByEmail(wtr.getPharmOrDermEmail());
		pharmacy.getPharmacists().add(pharmacistPharmacy);
		workTimeService.addWorkTime(wtr);
		return pharmacyRepository.save(pharmacy);
	}
	
	
	@Override
	public List<User> getPharmacists(Long id) { //radi, dobavlja sve na osnovu samo id apoteke
		Pharmacy pharmacy=findById(id);
		List<User> pharmacists = new ArrayList<User>();
		User user = userService.getCurrent();
		if(pharmacy.isUserPharmacyAdmin(user.getId())) {
			pharmacists = pharmacy.getPharmacists();
		}
		return pharmacists;	
	}
	
	public List<User> getAllPharmacistsFromPharmacy(Long id, String pharmacistEmail) {
		Pharmacy pharm = findById(id); //radi
		System.out.println(pharmacistEmail);
		List<User> pharmacists = pharm.getPharmacists();
		List<User> temp = new ArrayList<User>();
		
		User user = userService.getCurrent();
		if(pharm.isUserPharmacyAdmin(user.getId())) {
			System.out.println("usao");

			for(User p: pharmacists) {
				if(p.getEmail().toLowerCase().contains(pharmacistEmail.toLowerCase())) {
					System.out.println("usao");
					
					temp.add(p);
				}	
			}
		}  else {
			System.out.println("Nije pharmacy admin te apoteke");
			List<User> list = new ArrayList<User>();
			return list;
		}
		return temp;
	}
	
	public User findOnePharmacistFromPharmacy(Long id, String pharmacistSurname) {//radi
		Pharmacy pharm = findById(id);
		List<User> pharmacists = pharm.getPharmacists();

		User user = userService.getCurrent();
		if(pharm.isUserPharmacyAdmin(user.getId())) {
			for(User p: pharmacists) {
				if(p.getLastName().toLowerCase().equals(pharmacistSurname.toLowerCase())) {
					System.out.println("usao");
					return p;
				}	
			}
		}
		return null;
	}
	
	//dodati proveru je l admin apoteke
	public String deletePharmacistFromPharmacy(Long id, String pharmacistSurname) {//radi
		User p = findOnePharmacistFromPharmacy(id, pharmacistSurname);
		List<Counseling> counseling = counselingRepo.findAll();
		for(Counseling c: counseling) {
			if(c.getPharmacist().getLastName().equals(pharmacistSurname)) {
				return "ne moze se izbrisati jer ima zakazano savetovanje";
			} else {
				Pharmacy pharmacy = pharmacyService.findById(id);
				List<User> pharmacists = pharmacy.getPharmacists();
				pharmacists.remove(p);
				pharmacy = pharmacyRepository.save(pharmacy);
			}
		}
		return "ok";
	}
	
/*DERMATOLOZI***************************************************/
	public Pharmacy addPharmacyDerm(AddWorkTimeRequest wtr) { //ne radi dobro uslovm, tj. doda ipak bez id-ja
		Pharmacy pharmacy = pharmacyRepository.findById(wtr.getPharmacyId()).orElse(null);
		User pharmacyDermatologist = userService.findByEmail(wtr.getPharmOrDermEmail());
		pharmacy.getDermatologists().add(pharmacyDermatologist);
		WorkTime wt = workTimeService.addWorkTime(wtr);
		if(wt != null) {
			return pharmacyRepository.save(pharmacy);
		} else {
			return null;
		}
	}

	@Override
	public List<User> getDermatologists(Long id) { //imam
		Pharmacy pharmacy=findById(id);
		List<User> dermatologists = new ArrayList<User>();
		User user = userService.getCurrent();
		if(pharmacy.isUserPharmacyAdmin(user.getId())) {
			dermatologists = pharmacy.getDermatologists();
		}
		return dermatologists;
	}
	
	public List<User> getAllDermatologistsFromPharmacy(Long id, String dermEmail){ //imam
		Pharmacy pharm = findById(id);
		List<User> derms = pharm.getDermatologists();
		List<User> temp = new ArrayList<User>();
		
		User user = userService.getCurrent();
		if(pharm.isUserPharmacyAdmin(user.getId())) {
			for(User d: derms) {
				if(d.getEmail().toLowerCase().contains(dermEmail.toLowerCase())) {
					temp.add(d);
				}
			}
		} else {
			System.out.println("Nije pharmacy admin te apoteke");
			List<User> list = new ArrayList<User>();
			return list;
		}
		return temp;
	}
	
	public User findOneDermatologistFromPharmacy(Long id, String dermatologistSurname) {
		Pharmacy pharm = findById(id);
		List<User> derms = pharm.getDermatologists();
		
		User user = userService.getCurrent();
		if(pharm.isUserPharmacyAdmin(user.getId())) {
			for(User d: derms) {
				if(d.getLastName().toLowerCase().contains(dermatologistSurname.toLowerCase())) {
					return d;
				}
			}
		}
		
		return null;
	}
	
	//dodati proveru za admina
	public String deleteDermatologistFromPharmacy(Long id, String dermatologistSurname) {
		User dermatologist = findOneDermatologistFromPharmacy(id, dermatologistSurname);
		List<Examination> exams = examRepo.findAll();
		
		for(Examination e: exams) {
			if(e.getDermatologist().getId().equals(dermatologist.getId())) {
				return "ne moze se izbrisati jer ima zakazan pregled";
			} else {
				Pharmacy pharmacy = pharmacyService.findById(id);
				List<User> dermatologists = pharmacy.getDermatologists();
				dermatologists.remove(dermatologist);
				pharmacy = pharmacyRepository.save(pharmacy);
			}
		}
		return "ok";
	}
}
