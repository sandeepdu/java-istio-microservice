package com.app.pizza.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "_pizza")
public class Pizza {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private Size size;

	private Float price;

	protected Pizza() {
		// empty constructor required by JPA
	}

	public Pizza(String name, Size size, Float price) {
		this.name = name;
		this.size = size;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + name + ", size=" + size + ", price=" + price + "]";
	}

}
