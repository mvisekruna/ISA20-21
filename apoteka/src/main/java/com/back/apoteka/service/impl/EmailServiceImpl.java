package com.back.apoteka.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Promotion;
import com.back.apoteka.model.User;
import com.back.apoteka.request.CreateAbsenceRequest;
import com.back.apoteka.request.RegisterRequest;
import com.back.apoteka.request.SendPromotionRequest;
import com.back.apoteka.service.PromotionService;
@Service
public class EmailServiceImpl {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	Environment env;
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	PromotionService promoService;
	
	@Async
	public void sendNotificaitionAsync(RegisterRequest rr, Long id) throws MailException, InterruptedException, MessagingException {
		
		System.out.println("Slanje emaila...");
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

		String htmlMsg = "<h3>Hello "+rr.getName()+"</h3><br> <p>to activate Your account visit <a href=\"http://localhost:8080/api/activateacc/"+id.toString()+"\">link</a></p>";
		System.out.println(htmlMsg);
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(rr.getEmail());
		helper.setSubject("Verification");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendMedicineReservationConfirmation(String email, String firstName, Long id, Timestamp dateAndTime) throws MessagingException {
		System.out.println("Slanje emaila o leku...");
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

		String htmlMsg = "<h3>Hello "+firstName+"</h3><br> <p>We are informing You, that Your reservation "+id.toString()+" was successfuly made. You can take over till "+ dateAndTime.toString()+" </p>";
		System.out.println(htmlMsg);
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(email);
		helper.setSubject("Reservation");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
		System.out.println("Email poslat!");
		
	}

	@Async
	public void sendConsuelingConfirmation(String email, String firstName, String name, String address,
			Timestamp dateAndTime) throws MessagingException {
		System.out.println("Slanje emaila o savetovamju...");
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

		String htmlMsg = "<h3>Hello "+firstName+"</h3><br> <p>We are informing You, that You schedule consueling at "+name+" pharmacy, with address: "+address+". Your conuseling time is: "+ dateAndTime.toString()+" </p>";
		System.out.println(htmlMsg);
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(email);
		helper.setSubject("Reservation");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
		System.out.println("Email poslat!");
		
	}

	public void sendExaminationConfirmationMail(String email, String firstName, String name, String address,
			Date dateAndTime) throws MessagingException {
		System.out.println("Slanje emaila opregledu...");
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

		String htmlMsg = "<h3>Hello "+firstName+"</h3><br> <p>We are informing You, that You schedule examination at "+name+" pharmacy, with address: "+address+". Your examination time is: "+ dateAndTime.toString()+" </p>";
		System.out.println(htmlMsg);
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(email);
		helper.setSubject("Reservation");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
		System.out.println("Email poslat!");		
	}

	public void sendResponceToComplaint(String email, String answer) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

		String htmlMsg = "<h3>Hello</h3><br> <p>Here is answer for Your complaint<br>"+answer+" </p>";
		System.out.println(htmlMsg);
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(email);
		helper.setSubject("Complaint answer");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
		System.out.println("Email poslat!");		
		
	}
	
	public void sendNewPromotionNotification(String email, Long id) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		
		Promotion p = promoService.findById(id);
		
		String htmlMsg = "<h3>Hello</h3><br> <p>New promotion arrived: <br>"+p.getDescription()+" </p>";
		System.out.println(htmlMsg);
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(email);
		helper.setSubject("New promotion");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
		System.out.println("Email poslat!");		
	}
	
	public void sendNotificationAboutOfferToSupplier(String email) throws MessagingException { //obavestamo dobavljaca da je njegova ponuda prihvacena
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		
		String htmlMsg = "<h3>Hello</h3><br> <p>Your offer has been accepted<br> </p>";
		System.out.println(htmlMsg);
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(email);
		helper.setSubject("Offer notification");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
		System.out.println("Email poslat!");	
	}
	
	public void sendAbsenceRequestNotification(String email, Date startAbsence, Date endAbsence) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		
		String htmlMsg = "<h3>Hello</h3><br> <p>I would like to have leave of absence from <br>" + startAbsence + " to " + endAbsence + "</p>";
		System.out.println(htmlMsg);
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(email);
		helper.setSubject("Leave of absence");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
		System.out.println("Email poslat!");
	}
	
	
	public void sendAbsenceRequestApproved(String email) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		
		String htmlMsg = "<h3>Hello</h3><br> <p>Your leave of absence is approved!</p>";
		System.out.println(htmlMsg);
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(email);
		helper.setSubject("Leave of absence approval");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
		System.out.println("Email poslat!");
	}
	
	
	public void sendAbsenceRequestDenied(String email) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		
		String htmlMsg = "<h3>Hello</h3><br> <p>Your leave of absence is denied!</p>";
		System.out.println(htmlMsg);
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(email);
		helper.setSubject("Leave of absence denied");
		helper.setFrom(env.getProperty("spring.mail.username"));
		javaMailSender.send(mimeMessage);
		System.out.println("Email poslat!");
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
