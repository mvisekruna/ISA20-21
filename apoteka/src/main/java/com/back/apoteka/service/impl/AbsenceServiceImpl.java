package com.back.apoteka.service.impl;

import java.util.ArrayList;
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
		absence.setApproved(false);
		
		
		User user = userService.getCurrent();
		if(user.getAuthority().getName().equals("ROLE_PHARMACIST")) {
			absence.setPharmacist(user);
		} else {
			absence.setDermatologist(user);
		}
		//System.out.println(absence.getPharmacist().getId());
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
	
	@Override
	public List<Absence> findAllRequestsFromPharmacists() {
		List<Absence> absences = absenceRepository.findAll();
		List<Absence> temp = new ArrayList<Absence>();
		
		for(Absence a: absences) {
			if(a.getDermatologist()==null) {
				temp.add(a);
			}
		}
		return temp;
	}
	
	@Override
	public List<Absence> findAllRequestsFromDermatologists() {
		List<Absence> absences = absenceRepository.findAll();
		List<Absence> temp = new ArrayList<Absence>();
		
		for(Absence a: absences) {
			if(a.getPharmacist()==null) {
				temp.add(a);
			}
		}
		return temp;
	}
	
	@Override
	public void absenceApprovalForPharmacist(Long pharmacistId) {
		List<Absence> absences = findAllRequestsFromPharmacists();
		for(Absence a: absences) {
			if(a.getPharmacist().getId().equals(pharmacistId)) {
				a.setApproved(true);
				absenceRepository.save(a);
				try {
					emailService.sendAbsenceRequestApproved(a.getPharmacist().getEmail());
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	@Override
	public void absenceDeniedForPharmacist(Long pharmacistId) {
		List<Absence> absences = findAllRequestsFromPharmacists();
		for(Absence a: absences) {
			if(a.getPharmacist().getId().equals(pharmacistId)) {
				try {
					emailService.sendAbsenceRequestDenied(a.getPharmacist().getEmail());
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	@Override
	public void absenceApprovalForDermatologist(Long dermatologistId) {
		List<Absence> absences = findAllRequestsFromDermatologists();
		System.out.println(absences);
		for(Absence a: absences) {
			if(a.getDermatologist().getId().equals(dermatologistId)) {
				a.setApproved(true);
				absenceRepository.save(a);
				try {
					emailService.sendAbsenceRequestApproved(a.getDermatologist().getEmail());
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	@Override
	public void absenceDeniedForDermatologist(Long dermatologistId) {
		List<Absence> absences = findAllRequestsFromDermatologists();
		for(Absence a: absences) {
			if(a.getDermatologist().getId().equals(dermatologistId)) {
				try {
					emailService.sendAbsenceRequestDenied(a.getDermatologist().getEmail());
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}		
	}
}

