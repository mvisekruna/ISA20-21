package com.back.apoteka.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.User;
import com.back.apoteka.request.RegisterRequest;
@Service
public class EmailServiceImpl {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	Environment env;
	@Autowired
	UserServiceImpl userService;
	
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

}
