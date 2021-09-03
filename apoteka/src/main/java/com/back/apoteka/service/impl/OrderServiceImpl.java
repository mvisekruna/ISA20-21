package com.back.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Offer;
import com.back.apoteka.model.Order;
import com.back.apoteka.model.OrderStatus;
import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.model.User;
import com.back.apoteka.repository.OrderRepository;
import com.back.apoteka.request.OrderRequest;
import com.back.apoteka.service.OfferService;
import com.back.apoteka.service.OrderService;
import com.back.apoteka.service.PharmacyService;
import com.back.apoteka.service.UserService;

import java.sql.Timestamp;
//za narudzbinu daj mi sve ponude
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	PharmacyService pharmService;
	
	@Autowired
	OfferService offerService;
	
	@Autowired
	UserService userService;
	//dodati proveru za admina da li je za tu apoteku
	@Override
	public Order saveOrder(OrderRequest orderRequest) { //imam
		Order order = new Order();
		
		order.setDateOfEnd(Timestamp.valueOf(orderRequest.getDateOfEnd()));
		Pharmacy p = pharmService.findById(orderRequest.getPharmacyId());
		order.setPharm(p);
		order.setStatus(OrderStatus.WAITNG_FOR_OFFER);
		
		return orderRepo.save(order);
	}
	
	//imam
	public Order findById(Long id) throws AccessDeniedException {
		Order ord = orderRepo.findById(id).orElseGet(null);
		return ord;
	}

	@Override
	public List<Order> getOrdersFromPharmacy(Long pharmacyId) { //imam
		List<Pharmacy> pharms = pharmService.findAll();
		Pharmacy pharm = new Pharmacy();
		Date currentDate = new Date();
		for(Pharmacy p: pharms) {
			if(p.getId().equals(pharmacyId)) {
				pharm = p;
			}
		}
		User currentUser = userService.getCurrent();
		if(currentUser.getAuthority().getName().contains("ROLE_PATIENT")) {
			return null;
		}
		List<Order> orders = orderRepo.findAll();
		List<Order> temp = new ArrayList<Order>();
		
		for(Order o:orders) {
			if(o.getPharm().getId().equals(pharm.getId())) {
				temp.add(o);
				if(o.getDateOfEnd().before(currentDate)) {
					o.setExpired(true);
					orderRepo.save(o);
				}
				
				
			}
		}
		return temp;
	}

	@Override
	public List<Order> filtrateOrdersFromPharmacy(Long pharmacyId, int status) { 
		System.out.println(status);
		List<Pharmacy> pharms = pharmService.findAll();
		Pharmacy pharm = null;
		for(Pharmacy p: pharms) {
			if(p.getId().equals(pharmacyId)) {
				pharm = p;
			}
		}
		
		if(pharm == null) {
			return new ArrayList<Order>();
		}
		
		if(status == -1) {
			return getOrdersFromPharmacy(pharmacyId);
		}
		
		List<Order> orders = orderRepo.findAll();
		List<Order> temp = orderRepo.findAll();
		temp.removeAll(temp);
		for(Order o:orders) {
			if(o.getPharm().getId().equals(pharm.getId()) && o.getStatus() == convertToOrderStatus(status)) {
				temp.add(o);
			}
		}
		return temp;
	}
	
	private OrderStatus convertToOrderStatus(int status) {
		
		if(status == 0) {
			return OrderStatus.WAITNG_FOR_OFFER;
		}
		
		return OrderStatus.PROCESSED;
	}
	
	//delete narudzbenice i update
	@Override
	public String deleteOrder(Long orderId) {
		List<Offer> offers = offerService.getOffersForOrder(orderId);
		System.out.println(offers);
		System.out.println(offers.size());
		Order order = findById(orderId);
		System.out.println(order);
		List<Order> orders = orderRepo.findAll();
		if(offers.size() == 0) {
			orderRepo.delete(order);
			//kako da sacuvam nakon brisanja
			return "ok";
		}
		
	return "has offers";
	}

}
