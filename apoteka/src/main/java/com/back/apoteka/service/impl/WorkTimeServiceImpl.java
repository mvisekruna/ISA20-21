package com.back.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.User;
import com.back.apoteka.model.WorkTime;
import com.back.apoteka.repository.WorkTimeRepository;
import com.back.apoteka.request.AddWorkTimeRequest;
import com.back.apoteka.service.UserService;
import com.back.apoteka.service.WorkTimeService;

@Service
public class WorkTimeServiceImpl implements WorkTimeService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkTimeRepository workTimeRepo;
	
	@Override
	public List<WorkTime> findByDermatologist(Long dermatologistId) { //vraca sva radna vremena dermatologa
		List<WorkTime> worktimes = workTimeRepo.findAll();
		List<WorkTime> temp = workTimeRepo.findAll();
		temp.removeAll(temp);
		
		for(WorkTime wt: worktimes) {
			if(wt.getDermatologist().getId().equals(dermatologistId)) {
				temp.add(wt);
			}
		}
		return temp;
	}
	
	@Override
	public WorkTime addWorkTime(AddWorkTimeRequest wtr) {
		WorkTime wt = new WorkTime();
		wt.setDay(wtr.getDayWT());
		wt.setFrom(wtr.getFromWT());
		wt.setTo(wtr.getToWT());
		User user = userService.findByEmail(wtr.getPharmOdDermEmail());
		if(user.getAuthority().getName().contains("PHARMACIST")) {
			wt.setPharmacist(user);
		} else if(user.getAuthority().getName().contains("DERMATOLOGIST")) {
			boolean check = checkDermWorkTime(user.getId(), wtr.getFromWT(), wtr.getToWT(), wtr.getDayWT());
			System.out.println(check);//udje ovde ali ipak doda iz nekog razloga ponovo u listu dermatologa apoteke
			if(check) {
				wt.setDermatologist(user);
				wt = workTimeRepo.save(wt);
				return wt;
			}
		}
		return null;
	}
	
	public boolean checkDermWorkTime(Long dermId, Date from, Date to, Long day) {
		List<WorkTime> wts = workTimeRepo.findAll();
		List<WorkTime> temp = new ArrayList<WorkTime>();
		
		
		for(WorkTime w : wts) {
			
			if(w.getDermatologist() == null) {
				continue;
			}
			
			if(w.getDermatologist().getId().equals(dermId)) {
				temp.add(w);
			}
		}
		for(WorkTime wt : temp) {
				if(wt.getDay().equals(day)) {
					if(wt.getFrom().before(from) || wt.getTo().after(from) || wt.getFrom().before(to) || wt.getTo().after(to)) {
						System.out.println("usao u uslov");
						return false;
					}
				}
		}
		return true;
	}

}
