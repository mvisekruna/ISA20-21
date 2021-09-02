package com.back.apoteka.service.impl;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

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
	@Transactional
	public WorkTime addWorkTime(AddWorkTimeRequest wtr) {
		WorkTime wt = new WorkTime();
		wt.setDay(wtr.getDayWT());
		wt.setFrom(parseDate(wtr.getFromWT()));
		wt.setTo(parseDate(wtr.getToWT()));
		User user = userService.findByEmail(wtr.getPharmOrDermEmail());
		if(user.getAuthority().getName().contains("PHARMACIST")) {
			wt.setPharmacist(user);
			wt = workTimeRepo.save(wt);
		} else if(user.getAuthority().getName().contains("DERMATOLOGIST")) {
			boolean check = checkDermWorkTime(user.getId(), wt.getFrom(), wt.getTo(), wtr.getDayWT());
			System.out.println(check);
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
	
	public Date parseDate(String dateString) {
		
		String[] parts = dateString.split(":");
		Date date = new Timestamp(0,0,0,0,0,0,0);
		date.setHours(Integer.parseInt(parts[0]));
		date.setMinutes(Integer.parseInt(parts[1]));
		return date;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
