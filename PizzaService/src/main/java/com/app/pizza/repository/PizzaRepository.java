package com.app.pizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pizza.entity.Pizza;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
	List<Pizza> findById(Long Id);

	List<Pizza> findByName(String name);
}
