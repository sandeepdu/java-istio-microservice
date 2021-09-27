package com.app.pizza.service;

import com.app.pizza.entity.Order;

public interface OrderService {

    boolean isValid (Order oder);
    boolean isIdValid(long id);
}
