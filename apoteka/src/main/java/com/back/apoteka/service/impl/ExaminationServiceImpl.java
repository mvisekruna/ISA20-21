package com.back.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Examination;
import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.User;
import com.back.apoteka.model.WorkTime;
import com.back.apoteka.repository.ExaminationRepository;
import com.back.apoteka.repository.UserRepository;
import com.back.apoteka.repository.WorkTimeRepository;
import com.back.apoteka.request.ExaminationRequest;
import com.back.apoteka.request.ScheduleExaminationRequest;
import com.back.apoteka.response.CanUnscheduleResponce;
import com.back.apoteka.service.ExaminationService;
import com.back.apoteka.service.WorkTimeService;

@Service
public class ExaminationServiceImpl implements ExaminationService{

	@Autowired
	ExaminationRepository examinationRepo;
	@Autowired
	PharmacyServiceImpl pharmacyService;
	@Autowired
	UserServiceImpl userService;
	@Autowired
	WorkTimeService workTimeService;
	@Autowired
	WorkTimeRepository workTimeRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	EmailServiceImpl emailService;
	@Autowired
	CustomUserDetailsService customUserService;
	@Autowired
	PenaltyServiceImpl penaltyService;
	
	
	
	@Override
	public List<Examination> findByPharmacy(Long id) { //vraca samo slobodne termine za tu apoteku
		Pharmacy pharm = pharmacyService.findById(id);
		System.out.println(pharm);
		List<Examination> list= examinationRepo.findByPatient(null);
		List<Examination> list1= examinationRepo.findByPatient(null);
		for (Examination exam : list) {
			System.out.println(exam);
			if (exam.getPharmacy().getId()!=id) {
				list1.remove(exam);
			}
		}
		return list1;
	}
	
	@Override 
	public List<Examination> findByDermatologist(Long id) { //vraca samo slobodne termine za tog dermatologa
		List<Examination> list= examinationRepo.findByPatient(null);
		List<Examination> list1= examinationRepo.findByPatient(null);
		for (Examination exam: list) {
			System.out.println(exam);
			if(!exam.getDermatologist().getId().equals(id)) {
				list1.remove(exam);
			}
		}
		return list1;
	}
	
	public Examination getByDeramtologist(Long id) {
		List<Examination> exams = examinationRepo.findAll();
		Examination exam = new Examination();
		for(Examination e:exams) {
			if(e.getDermatologist().getId().equals(id)) {
				exam = e;
			}
		}
		return exam;
	}
	
	
	@Transactional
	public Examination save(ExaminationRequest examRequest) { 
		Examination exam = null; //u njega dodajemo krajni examination
		
		//provera da se ne poklapa sa vec zakazanim
		List<Examination> exams = examinationRepo.findAll(); //dobavim sve preglede prvo da bih proverila da nema vec zakazan tad 
		for(Examination e:exams) {
			if(e.getDermatologist().getId().equals(examRequest.getDermatologistId())) { //prvo uporedjujem dermatologa za kojeg zakazujem, da vidim je l ima vec nesto zakazano
				//ako ulazi u if onda proveravamo dan da li je isti sto prosledjujem
				Date d = new Date(e.getDateAndTime().getTime() + (e.getDuration() * 60000)); //dodaje trajanje na pocetak pregleda
				if(examRequest.getDateAndTime().getDay() == e.getDateAndTime().getDay()) { //ako udje u ovo, proverava vreme da li upada u termin vec zakazanog
					if(examRequest.getDateAndTime().after(e.getDateAndTime()) || examRequest.getDateAndTime().before(d)){
						System.out.println("desio se null");																								//da li upada u vec zakazani termin odredjenog dermatologa
						return null; //vraca null jer jer upada u termin ako udje u taj if, ako ne nastavlja dalje
					}
				}
			}
		}
		
		//provera da li prosledjen termin ulazi u radno vreme dermatologa apoteke 
		List<WorkTime> worktimes = workTimeService.findByDermatologist(examRequest.getDermatologistId()); //vrati sva radna vremena dermatologa u svim apotekama
		boolean found = false;
		for(WorkTime wt: worktimes) { 
			
			
			Calendar c = Calendar.getInstance();
			c.setTime(examRequest.getDateAndTime());
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			
			System.out.println(wt.getDay());
			System.out.println(dayOfWeek);
			
			if(wt.getDay() != dayOfWeek) {
				continue;
			}
			
			int minutesExam = examRequest.getDateAndTime().getHours() * 60 + examRequest.getDateAndTime().getMinutes();
			int minutesFrom = wt.getFrom().getHours() * 60 + wt.getFrom().getMinutes();
			int minutesTo = wt.getTo().getHours() * 60 + wt.getTo().getMinutes();
			System.out.println(minutesExam);
			System.out.println(minutesFrom);
			System.out.println(minutesTo);
			if(minutesExam >= minutesFrom && minutesExam <= minutesTo) {
				
				User dermatologist = userService.findById(examRequest.getDermatologistId());
				exam = new Examination();
				exam.setDermatologist(dermatologist);
				exam.setDateAndTime(examRequest.getDateAndTime());
				found = true;
				break;
			}
		}
		System.out.println(found);
		if(found) {
			
			exam.setDuration(examRequest.getDuration());
			exam.setPrice(examRequest.getPrice());
			exam.setReport("");
			exam.setExecuted(false);
			exam.setDidntShow(false);
			exam.setPatient(null);

			return examinationRepo.save(exam);
		} else {
			return null;
		}
	}
	
	@Override
	@Transactional
	public Examination schedule(ScheduleExaminationRequest schedule) {
		System.out.println("usao u examschedule");
		Examination exam = examinationRepo.findById(schedule.getExamId()).orElse(null);
		User patient= userRepo.findByEmail(schedule.getPatientEmail());
		exam.setPatient(patient);
		System.out.println(exam.getPatient());
		System.out.println(exam);
		try {
			emailService.sendExaminationConfirmationMail(patient.getEmail(), patient.getFirstName(), exam.getPharmacy().getName(), exam.getPharmacy().getAddress(), exam.getDateAndTime());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return examinationRepo.save(exam);
	}

	
	@Override
	public List<CanUnscheduleResponce> getScheduled() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		User u = (User) customUserService.loadUserByUsername(currentUser.getName());
		 
		Date date= new Date();
		long time = date.getTime();
		java.sql.Timestamp currTime = new java.sql.Timestamp(time);
		List<Examination> exams=examinationRepo.findByPatient(u);
		List<Examination> temp = examinationRepo.findByPatient(u);
		List<CanUnscheduleResponce> canUnsch= new ArrayList<CanUnscheduleResponce>();	
		for (Examination e: temp) {
			System.out.println(currTime + " " + e.getDateAndTime());
			if(!(currTime).before(e.getDateAndTime())) {
				exams.remove(e);
			}
			else {
				CanUnscheduleResponce cue = new CanUnscheduleResponce();
				cue.setExam(e);
				cue.setCanUnschedule(canUnschedule(e.getId()));
				System.out.println(cue);
				canUnsch.add(cue);
			}
		}
		return canUnsch;
	}
	public boolean canUnschedule(Long examId) { //proverava da li pregled sa tim idijem moze da se otkaze
		//tj da li do njega ima vise od 24h
		//disejblovati dugme za otkazivanje rezervacije za takve preglede
		Examination exam = examinationRepo.findById(examId).orElse(null);
		Date date1= new Date();
		Date date = new Date(date1.getTime() + 3600*1000*24);
		long time = date.getTime();
		java.sql.Timestamp timeUnschedule = new java.sql.Timestamp(time);
		if (timeUnschedule.before(exam.getDateAndTime())) {
			return true;
		}
		return false;
	}

	public Examination unschedule(ScheduleExaminationRequest schedule) {
		Examination exam = examinationRepo.findById(schedule.getExamId()).orElse(null);
		exam.setPatient(null);
		return examinationRepo.save(exam);
	}

	public List<Examination> historyOfExaminations() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		User u = (User) customUserService.loadUserByUsername(currentUser.getName()); 
		Date date= new Date();
		long time = date.getTime();
		java.sql.Timestamp currTime = new java.sql.Timestamp(time);
		List<Examination> temp=examinationRepo.findByPatient(u);
		
		List<Examination> lista= new ArrayList<Examination>();
		
		for (Examination e : temp) {
			if (currTime.after(e.getDateAndTime())) {
				lista.add(e);
			}
		}
		return lista;
	}

	public List<User> getDermatologistsIMet()//vraca dermatologe sa kojima je imao iskustva
	//da moze da pise zalbe
	{
		List<Examination> examinations = historyOfExaminations();
		System.out.println(examinations.size());
		List<User> dermatologists = new ArrayList<>();
		for (Examination exam: examinations) {
			if (!dermatologists.contains(exam.getDermatologist())) {
				System.out.println("usao u if");
				dermatologists.add(exam.getDermatologist());
			}
		}
		return dermatologists;
	}

	
	
	public List<Examination> historyOfExaminationsDerm() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		User u = (User) customUserService.loadUserByUsername(currentUser.getName()); 
		Date date= new Date();
		long time = date.getTime();
		java.sql.Timestamp currTime = new java.sql.Timestamp(time);
		List<Examination> temp=examinationRepo.findByDermatologist(u);
		
		List<Examination> lista= new ArrayList<Examination>();
		
		for (Examination e : temp) {
			if (currTime.after(e.getDateAndTime())) {
				lista.add(e);
			}
		}
		return lista;
	}

	public List<Examination> scheduleForDermatologist() { //vraca samo zakazane tremine kod tog dermatologa

		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		User dermatologist = (User) customUserService.loadUserByUsername(currentUser.getName()); 
		List<Examination> lista = examinationRepo.findByDermatologist(dermatologist);
		System.out.println(lista.size());
		List<Examination> exams = new ArrayList<Examination>();
		Date date= new Date();
		long time = date.getTime();
		java.sql.Timestamp currTime = new java.sql.Timestamp(time);
		for(Examination e: lista) {
			if (currTime.before(e.getDateAndTime()) && !e.isDidntShow() && !e.isExecuted()) {
				System.out.println("usao u f");
				exams.add(e);
			}
		}
		return exams;
	}

	
	public Examination didntShow(Examination exam) {
		//exam.setDidntShow(true);
		System.out.println(exam.toString());
		System.out.println(exam.getId());
		User patient = exam.getPatient();
		penaltyService.addPenalty(patient);
		exam.setDidntShow(true);
		return examinationRepo.save(exam);
	}

	public Examination finish(Examination exam) {
		Examination e = examinationRepo.findById(exam.getId()).orElse(null);
		e.setReport(exam.getReport());
		e.setDidntShow(false);
		e.setExecuted(true);
		return examinationRepo.save(e);
	}
}
