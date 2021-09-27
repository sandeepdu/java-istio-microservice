package com.app.pizza.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "_order")
public class Order {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	private String recipient;

	private Float totalPrice;

	protected Order() {

	}

	public Order(String recipient, float totalPrice, List<OrderItem> orderItems) {
		this.recipient = recipient;
		this.totalPrice = totalPrice;
		this.orderItems = orderItems;
	}

	public Long getId() {
		return id;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderItems=" + orderItems + ", recipient=" + recipient + ", totalPrice="
				+ totalPrice + "]";
	}

}
