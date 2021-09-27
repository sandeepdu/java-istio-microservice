package com.app.pizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pizza.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
