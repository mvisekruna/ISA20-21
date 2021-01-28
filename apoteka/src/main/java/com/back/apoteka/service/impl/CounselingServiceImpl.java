package com.back.apoteka.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Counseling;
import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.User;
import com.back.apoteka.repository.CounselingRepository;
import com.back.apoteka.request.ScheduleCounselingRequest;
import com.back.apoteka.request.ScheduleExaminationRequest;
import com.back.apoteka.response.CanCancelCounselingResponse;
import com.back.apoteka.service.CounselingService;
@Service
public class CounselingServiceImpl implements CounselingService{

	@Autowired
	CounselingRepository counselingRepo;
	@Autowired
	PharmacyServiceImpl pharmacyService;

	@Autowired
	CustomUserDetailsService customUserService;
	@Override
	public List<Counseling> findByPharmacy(Long id) {
		Pharmacy pharm = pharmacyService.findById(id);
		System.out.println(pharm);
		List<Counseling> list= counselingRepo.findByPatient(null);
		List<Counseling> list1= counselingRepo.findByPatient(null);
		for (Counseling coun : list) {
			System.out.println(coun);
			if (coun.getPharmacy().getId()!=id) {
				list1.remove(coun);
			}
		}
		return list1;
	}
	@Override
	public List<CanCancelCounselingResponse> getScheduleCounseling() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		User u = (User) customUserService.loadUserByUsername(currentUser.getName());
		 
		Date date= new Date();
		long time = date.getTime();
		java.sql.Timestamp currTime = new java.sql.Timestamp(time);
		List<Counseling> couns=counselingRepo.findByPatient(u);
		List<Counseling> temp = counselingRepo.findByPatient(u);
		List<CanCancelCounselingResponse> canCanc= new ArrayList<CanCancelCounselingResponse>();	
		for (Counseling c: temp) {
			System.out.println(currTime + " " + c.getDateAndTime());
			if(!(currTime).before(c.getDateAndTime())) {
				couns.remove(c);
			}
			else {
				CanCancelCounselingResponse ccc = new CanCancelCounselingResponse();
				ccc.setCounseling(c);
				ccc.setCanCancel(canCancel(c.getId()));
				System.out.println(ccc);
				canCanc.add(ccc);
			}
		}
		return canCanc;
	}
	public boolean canCancel(Long counId) { //proverava da li saveovanje sa tim idijem moze da se otkaze
		//tj da li do njega ima vise od 24h
		Counseling exam = counselingRepo.findById(counId).orElse(null);
		Date date1= new Date();
		Date date = new Date(date1.getTime() + 3600*1000*24);
		long time = date.getTime();
		java.sql.Timestamp timeCancel = new java.sql.Timestamp(time);
		if (timeCancel.before(exam.getDateAndTime())) {
			return true;
		}
		return false;
	}
	
	public Counseling unschedule(ScheduleExaminationRequest schedule) {
		Counseling coun = counselingRepo.findById(schedule.getExamId()).orElse(null);
		coun.setPatient(null);
		return counselingRepo.save(coun);
	}
	@Autowired
	UserServiceImpl userService;
	@Autowired
	EmailServiceImpl emailService;
	@Override
	public boolean schedule(ScheduleCounselingRequest scr) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		User patient = (User) customUserService.loadUserByUsername(currentUser.getName());
		
		Counseling coun = new Counseling();
		coun.setDateAndTime(Timestamp.valueOf(scr.getDateAndTime()));
		coun.setPatient(patient);
		coun.setPharmacist(userService.findById(scr.getPharmacist()));
		coun.setPharmacy(pharmacyService.findById(scr.getPharmacyId()));
		counselingRepo.save(coun);
		
		try {
			emailService.sendConsuelingConfirmation(patient.getEmail(), patient.getFirstName(), coun.getPharmacy().getName(), coun.getPharmacy().getAddress(), coun.getDateAndTime());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
