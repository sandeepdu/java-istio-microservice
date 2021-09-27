package com.app.pizza.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "_orderItem")
public class OrderItem {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private Long pizzaId;

	private Float price;
	
	private Integer quantity;

	protected OrderItem() {
	}

	public OrderItem(Long pizzaId, String name, Float price) {
		this.pizzaId = pizzaId;
		this.name = name;
		this.price = price;
		this.quantity = 1;	//by default 1
	}

	public Long getId() {
		return id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(Long pizzaId) {
		this.pizzaId = pizzaId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
