package com.back.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.Order;
import com.back.apoteka.model.OrderStatus;
import com.back.apoteka.request.OrderRequest;
import com.back.apoteka.service.OrderService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public Order saveOrder(@RequestBody OrderRequest orderRequest) {
		return orderService.saveOrder(orderRequest);
	}
	
	@GetMapping("/findOneOrder/{orderId}") 
	public Order findById(@PathVariable Long orderId) {
		return orderService.findById(orderId);
	}
	
	@GetMapping("/{pharmacyId}")
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public List<Order> getOrdersFromPharmacy(@PathVariable Long pharmacyId){
		return orderService.getOrdersFromPharmacy(pharmacyId);
	}
	
	//dropdown na frontu pa da ima izbor
	@GetMapping("/filtrate/{pharmacyId}/{status}")
	public List<Order> filtrateOrdersFromPharmacy(@PathVariable Long pharmacyId, @PathVariable int status) { 
		return orderService.filtrateOrdersFromPharmacy(pharmacyId, status);
	}
	
	
	
	
	
	

}
