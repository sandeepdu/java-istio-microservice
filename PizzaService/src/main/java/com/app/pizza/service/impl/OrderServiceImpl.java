package com.app.pizza.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.pizza.entity.Order;
import com.app.pizza.entity.OrderItem;
import com.app.pizza.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Override
	public boolean isValid(Order order) {
		return order != null && order.getRecipient() != null && isOrderItemValid(order.getOrderItems());
	}

	boolean isOrderItemValid(List<OrderItem> orderItems) {
		for (OrderItem orderItem : orderItems)
			return orderItem != null && orderItem.getQuantity() != 0;
		return false;
	}

	@Override
	public boolean isIdValid(long id) {
		return id != 0;
	}
}
