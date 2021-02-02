package com.back.apoteka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Complaint;
import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.User;
import com.back.apoteka.repository.ComplaintRepository;
import com.back.apoteka.request.ComplaintRequest;
import com.back.apoteka.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired 
	ComplaintRepository complaintRepository;
	@Autowired
	CustomUserDetailsService customUserService;
	@Autowired
	UserServiceImpl userService;
	@Override
	public Complaint writeComplaintForStaff(ComplaintRequest cr) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		User patient = (User) customUserService.loadUserByUsername(currentUser.getName());
		
		User staff = userService.findById(cr.getId());
		Complaint complaint = new Complaint();
		complaint.setPharmacy(null);
		complaint.setPatient(patient);
		complaint.setStaff(staff);
		complaint.setMessage(cr.getMessage());

		return complaintRepository.save(complaint);
	}
	@Autowired
	PharmacyServiceImpl pharmacyService;
	@Override
	public Complaint writeComplaintForPharmacy(ComplaintRequest cr) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		User patient = (User) customUserService.loadUserByUsername(currentUser.getName());
		
		Pharmacy pharm=pharmacyService.findById(cr.getId());
		Complaint complaint = new Complaint();
		complaint.setPatient(patient);
		complaint.setStaff(null);
		complaint.setPharmacy(pharm);
		complaint.setMessage(cr.getMessage());
		return complaintRepository.save(complaint);



	}
}
