package com.back.apoteka.service.impl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Order;
import com.back.apoteka.model.OrderStatus;
import com.back.apoteka.model.Pharmacy;
import com.back.apoteka.repository.OrderRepository;
import com.back.apoteka.request.OrderRequest;
import com.back.apoteka.service.OrderService;
import com.back.apoteka.service.PharmacyService;

import java.sql.Timestamp;
//za narudzbinu daj mi sve ponude
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	PharmacyService pharmService;
	
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
		for(Pharmacy p: pharms) {
			if(p.getId().equals(pharmacyId)) {
				pharm = p;
			}
		}
		List<Order> orders = orderRepo.findAll();
		List<Order> temp = new ArrayList<Order>();
		
		for(Order o:orders) {
			if(o.getPharm().getId().equals(pharm.getId())) {
				temp.add(o);
			}
		}
		return temp;
	}

	@Override
	public List<Order> filtrateOrdersFromPharmacy(Long pharmacyId, int status) { 
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
		
		List<Order> orders = orderRepo.findAll();
		List<Order> temp = orderRepo.findAll();
		temp.removeAll(temp);
		for(Order o:orders) {
			if(o.getPharm().getId().equals(pharm.getId())) {
				
				if(o.getStatus() == convertToOrderStatus(status)) {
					temp.add(o);
				}
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

}
