package com.app.pizza.service;

import com.app.pizza.entity.Pizza;

public interface PizzaService {
    boolean isValid(Pizza pizza);
    boolean isIdValid (long id);
}
