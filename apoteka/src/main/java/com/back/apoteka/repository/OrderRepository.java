package com.back.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.apoteka.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
