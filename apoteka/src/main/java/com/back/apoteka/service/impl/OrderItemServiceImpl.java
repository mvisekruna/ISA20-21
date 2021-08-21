package com.back.apoteka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.apoteka.model.Medicine;
import com.back.apoteka.model.Order;
import com.back.apoteka.model.OrderItem;
import com.back.apoteka.request.AddOrderItemRequest;
import com.back.apoteka.service.MedicineService;
import com.back.apoteka.service.OrderItemService;
import com.back.apoteka.service.OrderService;
import com.back.apoteka.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	MedicineService medService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderItemRepository orderItemRepo;
	
	@Override
	public OrderItem save(AddOrderItemRequest oir) {
		OrderItem item = new OrderItem();
		item.setAmount(oir.getAmount());
		Medicine med = medService.findById(oir.getMedicineId());
		item.setMed(med);
		Order order = orderService.findById(oir.getOrderId());
		item.setOrder(order);
		
		return orderItemRepo.save(item);
	}

}
