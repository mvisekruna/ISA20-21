package com.back.apoteka.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Absence;
import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.User;
import com.back.apoteka.repository.AbsenceRepository;
import com.back.apoteka.request.CreateAbsenceRequest;
import com.back.apoteka.service.AbsenceService;
import com.back.apoteka.service.PharmacyService;
import com.back.apoteka.service.UserService;

@Service
public class AbsenceServiceImpl implements AbsenceService {

	@Autowired
	UserService userService;
	
	@Autowired
	EmailServiceImpl emailService;
	
	@Autowired
	AbsenceRepository absenceRepository;
	
	@Autowired
	PharmacyService pharmacyService;
	
	
	@Override
	public Absence sendRequestForAbsence(CreateAbsenceRequest createAbsenceRequest) {
		Absence absence = new Absence();
		System.out.println(createAbsenceRequest.getStartAbsence());
		absence.setStartAbsence(createAbsenceRequest.getStartAbsence());
		absence.setEndAbsence(createAbsenceRequest.getEndAbsence());
		
		
		User user = userService.getCurrent();
		if(user.getAuthority().getName().equals("ROLE_PHARMACIST")) {
			absence.setPharmacist(user);
		} else {
			absence.setDermatologist(user);
		}
		System.out.println(absence.getPharmacist().getId());
		List<User> systemAdmins = userService.findAll();
		if(absence.getPharmacist()==(null)) { //dermatolog trazi odsustvo ako je farmaceut nula
			for(User u:systemAdmins) { //prolazi kroz usere 
				if(u.getAuthority().getName().equals("SYSTEM_ADMIN")) { //trazi sistem admine
					try {
						emailService.sendAbsenceRequestNotification(u.getEmail(), absence.getStartAbsence(), absence.getEndAbsence());

					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			} 
			
		} else if(absence.getDermatologist()==null) { //ako je dermatolog nula, znaci farmaceut trazi 
			Pharmacy p = findPharmacy(absence.getPharmacist().getId());
			System.out.println(p);
			List<User> pharmacyAdmins = p.getAdminApoteke(); //vraca listu admina odredjene apoteke
			for (User u: pharmacyAdmins) { //svima njima salje mail, jer su svi admini apoteke
				try {
					emailService.sendAbsenceRequestNotification(u.getEmail(), absence.getStartAbsence(), absence.getEndAbsence());
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
		return absenceRepository.save(absence);
	}
	
	public Pharmacy findPharmacy(Long pharmacistId) {
		List<Pharmacy> pharmacies = pharmacyService.findAll();
		Pharmacy pharmacy = null;
		for(Pharmacy p: pharmacies) {
			List<User> pharmacists = p.getPharmacists();
			for(User u:pharmacists) {
				if(u.getId().equals(pharmacistId)) {
					pharmacy = p;
				}
			}
		}
		return pharmacy;
	}
	
	
	
}

