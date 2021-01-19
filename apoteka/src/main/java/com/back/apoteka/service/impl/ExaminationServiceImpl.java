package com.back.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Examination;
import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.repository.ExaminationRepository;
import com.back.apoteka.service.ExaminationService;

@Service
public class ExaminationServiceImpl implements ExaminationService{

	@Autowired
	ExaminationRepository examinationRepo;
	@Autowired
	PharmacyServiceImpl pharmacyService;
	
	@Override
	public List<Examination> findByPharmacy(Long id) {
		Pharmacy pharm = pharmacyService.findById(id);
		System.out.println(pharm);
		List<Examination> list= examinationRepo.findAll();
		List<Examination> list1= examinationRepo.findAll();
		for (Examination exam : list) {
			System.out.println(exam);
			if (exam.getPharmacy().getId()!=id) {
				list1.remove(exam);
			}
		}
		return list1;
	}

	@Override
	public List<Examination> findByDermatologist(Long id) {
		List<Examination> list= examinationRepo.findAll();
		List<Examination> list1= examinationRepo.findAll();
		for (Examination exam: list) {
			System.out.println(exam);
			if(!exam.getDermatologist().getId().equals(id)) {
				list1.remove(exam);
			}
		}
		return list1;
	}

}
