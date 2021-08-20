package com.back.apoteka.service;

import java.util.List;

import com.back.apoteka.model.Order;
import com.back.apoteka.model.OrderStatus;
import com.back.apoteka.request.OrderRequest;

public interface OrderService {
	
	Order saveOrder(OrderRequest orderRequest);
	
	Order findById(Long id);
	
	List<Order> getOrdersFromPharmacy(Long pharmacyId);
	
	List<Order> filtrateOrdersFromPharmacy(Long pharmacyId, int status);
	
	

}
