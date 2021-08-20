package com.back.apoteka.service;

import java.util.List;

import com.back.apoteka.model.User;
import com.back.apoteka.model.WorkTime;
import com.back.apoteka.request.AddWorkTimeRequest;

public interface WorkTimeService {
	public List<WorkTime> findByDermatologist(Long dermatologistId);
	WorkTime addWorkTime(AddWorkTimeRequest wtr);
	

}
