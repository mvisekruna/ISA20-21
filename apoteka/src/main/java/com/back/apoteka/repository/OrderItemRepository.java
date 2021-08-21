package com.back.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.apoteka.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
