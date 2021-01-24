package com.back.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.Counseling;
import com.back.apoteka.request.ScheduleCounselingRequest;
import com.back.apoteka.request.ScheduleExaminationRequest;
import com.back.apoteka.response.CanCancelCounselingResponse;
import com.back.apoteka.service.impl.CounselingServiceImpl;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/counseling")
public class CounselingController {

	@Autowired
	CounselingServiceImpl counselingService;
	
	@GetMapping("/schedule")
	public List<CanCancelCounselingResponse> scheduledCounselings(){
		System.out.println("usao u getScheudle");
		return counselingService.getScheduleCounseling();
	}
	@PostMapping("/schedule")
	public boolean schedule(@RequestBody ScheduleCounselingRequest scr) {
		return counselingService.schedule(scr);
	}
	@PostMapping("/unschedule")
	public Counseling unscheduleExamination(@RequestBody ScheduleExaminationRequest schedule) {
		System.out.println("usao u coun unschedule contr");
		return counselingService.unschedule(schedule);
	}
}
