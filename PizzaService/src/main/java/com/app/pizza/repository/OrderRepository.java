package com.app.pizza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pizza.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByRecipient(String recipient);
}
