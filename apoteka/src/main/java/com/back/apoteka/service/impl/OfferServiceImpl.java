package com.back.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Offer;
import com.back.apoteka.model.OfferStatus;
import com.back.apoteka.model.Order;
import com.back.apoteka.model.OrderStatus;
import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.User;
import com.back.apoteka.repository.OfferRepository;
import com.back.apoteka.repository.OrderRepository;
import com.back.apoteka.request.OfferRequest;
import com.back.apoteka.service.OfferService;
import com.back.apoteka.service.OrderService;
import com.back.apoteka.service.PharmacyService;
import com.back.apoteka.service.UserService;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OfferRepository offerRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	EmailServiceImpl emailService;
	
	@Autowired
	PharmacyService pharmacyService;
	
	
	@Override
	public Offer save(OfferRequest or) {
		Offer offer = new Offer(); // ovako mora, ne == null
		
		User supplier = userService.getCurrent();
		offer.setSupplier(supplier);
		
		Order order = orderService.findById(or.getOrderId());
		offer.setOrder(order);
		
		offer.setPrice(or.getPrice());
		
		
		offer.setStatus(OfferStatus.WAITING);
		
		return offerRepo.save(offer);
	}


	@Override
	public List<Offer> getOffersForOrder(Long orderId) {
		User user = userService.getCurrent();
		Order or = orderService.findById(orderId);
		Pharmacy pharmacy = pharmacyService.findById(or.getPharm().getId());
		if(user.getAuthority().getName().equals("ROLE_PHARMACY_ADMIN")) { //mozda mi sad ni ne treba ova provera al nema veze
			if(pharmacy.isUserPharmacyAdmin(user.getId())) {
				List<Offer> offers = offerRepo.findAll();
				List<Offer> temp = offerRepo.findAll();
				
				temp.removeAll(temp);
				for(Offer o:offers) {
					if(o.getOrder().getId().equals(or.getId())) {
						temp.add(o);
					}
				}
				return temp;
			}
		}
		System.out.println("nije admin apoteke");
		return Collections.emptyList();
	}
	
	@Override 
	public Offer pickOneOffer(Long offerId) {
		Offer temp = offerRepo.findById(offerId).orElseGet(null);
		System.out.println(temp.getId());
		List<Offer> offers = getOffersForOrder(temp.getOrder().getId()); //dobijem sve ponude za narudzbenicu 
		Offer offer = null; //u njega smestamo krajnji
		//na frontu proveravamo je l isteklo vreme davanja ponude
		for(Offer o: offers) {
			System.out.println(o);
			if(o.getId().equals(temp.getId())) {  //ako je izabrana ta ponuda
				o.setStatus(OfferStatus.ACCEPTED);
				offer = o;
				System.out.println(o.getStatus());
				Order order = orderService.findById(temp.getOrder().getId());
				order.setStatus(OrderStatus.PROCESSED);
				orderRepo.save(order);
								
			} else {
				System.out.println("usao");
				o.setStatus(OfferStatus.CANCELED);
				
			}
			offerRepo.save(o);
			
			User u = o.getSupplier(); //dobijemo dobavljaca
			try {
				emailService.sendNotificationAboutOfferToSupplier(u.getEmail()); //obavestimo ga da je njegova ponuda izabrana 
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		
		}
		return offer; 
	}
	
	@Override
	public void checkOffersEndDate(Long pharmacyId) {
		List<Order> orders = orderService.getOrdersFromPharmacy(pharmacyId);
		Date currentDate = new Date();
		System.out.println(currentDate);
		
		
		for(Order o: orders) {
			if(o.getDateOfEnd().before(currentDate)) {
				System.out.println("usao");
				o.setExpired(true);
				System.out.println(o);
			}
		}
	}
		
}
			
			
		
		

