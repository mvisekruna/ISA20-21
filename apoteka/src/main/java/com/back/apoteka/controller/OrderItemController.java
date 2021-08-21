package com.back.apoteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.OrderItem;
import com.back.apoteka.request.AddOrderItemRequest;
import com.back.apoteka.service.impl.OrderItemServiceImpl;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/orderitem")
public class OrderItemController {
	
	@Autowired
	OrderItemServiceImpl orderItemService;
	
	@PostMapping
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public OrderItem createOrderItem(@RequestBody AddOrderItemRequest oir) {
		return orderItemService.save(oir);
	}
	

}
