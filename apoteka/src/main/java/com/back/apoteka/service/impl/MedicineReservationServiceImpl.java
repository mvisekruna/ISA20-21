package com.back.apoteka.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Examination;
import com.back.apoteka.model.Medicine;
import com.back.apoteka.model.MedicineReservation;
import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.User;
import com.back.apoteka.repository.MedicineReservationRepository;
import com.back.apoteka.request.MedicineReservationRequest;
import com.back.apoteka.request.ScheduleExaminationRequest;
import com.back.apoteka.response.CanCancelReservationResponce;
import com.back.apoteka.service.MedicineReservvationService;
@Service
public class MedicineReservationServiceImpl implements MedicineReservvationService {

	@Autowired
	MedicineReservationRepository medicineReservationRepo;
	@Autowired
	MedicineServiceImpl medicineService;
	@Autowired
	PharmacyServiceImpl pharmacyService;
	@Autowired
	CustomUserDetailsService customUserService;
	@Override
	public boolean save(MedicineReservationRequest mrr) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		User patient = (User) customUserService.loadUserByUsername(currentUser.getName());
		MedicineReservation mr= new MedicineReservation();
		Medicine medicine = medicineService.findById(mrr.getMedicineId());
		Pharmacy pharmacy = pharmacyService.findById(mrr.getPharmacyId());
		mr.setDateAndTime(Timestamp.valueOf(mrr.getDateAndTime()));
		mr.setMedicine(medicine);
		mr.setPatient(patient);
		mr.setPharmacy(pharmacy);
		System.out.println("cua rez");
		medicineReservationRepo.save(mr);
		return true;
	}

	public List<CanCancelReservationResponce> getReservations(){//proveri
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		User patient = (User) customUserService.loadUserByUsername(currentUser.getName());
		Date date= new Date();
		long time = date.getTime();
		java.sql.Timestamp currTime = new java.sql.Timestamp(time);
		List<CanCancelReservationResponce> responce= new ArrayList<CanCancelReservationResponce>();
		List<MedicineReservation> reservations = medicineReservationRepo.findAll();
		List<MedicineReservation> temp = medicineReservationRepo.findAll();
		for(MedicineReservation mr: temp) {
			if (!mr.getPatient().equals(patient) || !(currTime).before(mr.getDateAndTime())) {
				reservations.remove(mr);
			}
			else {
				CanCancelReservationResponce ccrr= new CanCancelReservationResponce();
				ccrr.setMr(mr);
				ccrr.setCanCancel(canCancel(mr.getId()));
				responce.add(ccrr);
			}
		}
	
		return responce;
	}
	
	public boolean canCancel(Long id){
		Date date1= new Date();
		Date date = new Date(date1.getTime() + 3600*1000*24);
		long time = date.getTime();
		java.sql.Timestamp timeUnschedule = new java.sql.Timestamp(time);
		MedicineReservation mr= medicineReservationRepo.findById(id).orElse(null);
		if (timeUnschedule.before(mr.getDateAndTime())) {
			return true;
		}
		return false;
	}
	public boolean cancel(ScheduleExaminationRequest schedule) {
		MedicineReservation mr = medicineReservationRepo.findById(schedule.getExamId()).orElse(null);
		mr.setPatient(null);
		medicineReservationRepo.delete(mr);
		return true;
	}
}
