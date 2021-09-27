package com.app.pizza.service.impl;

import org.springframework.stereotype.Service;

import com.app.pizza.entity.Pizza;
import com.app.pizza.service.PizzaService;

@Service
public class PizzaServiceImpl implements PizzaService {
    @Override
    public boolean isValid(Pizza pizza) {
        return pizza!=null && pizza.getName() !=null && pizza.getPrice()!=null && pizza.getSize()!=null;
    }

    @Override
    public boolean isIdValid(long id) {
        return id != 0;
    }
}
