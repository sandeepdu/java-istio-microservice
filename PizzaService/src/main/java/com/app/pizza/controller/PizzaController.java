package com.app.pizza.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.pizza.entity.Pizza;
import com.app.pizza.entity.request.PizzaRequest;
import com.app.pizza.repository.PizzaRepository;
import com.app.pizza.service.PizzaService;

@RestController
public class PizzaController {

	Logger logger = LoggerFactory.getLogger(PizzaController.class);

	@Autowired
	private PizzaRepository pizzaRepository;
	@Autowired
	private PizzaService pizzaService;

	public PizzaController(PizzaRepository pizzaRepository) {
		this.pizzaRepository = pizzaRepository;
	}

	/* 
	 * get all pizzas detail
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/pizza", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPizzas() {
		logger.info("inside getPizzas()");
		List<Pizza> pizzaList = pizzaRepository.findAll();
		logger.info("called pizzaRepository.findAll");
		logger.debug("orderRepository pizzaList.size {}", pizzaList.size());
		List<Long> pizzaId = new ArrayList<Long>();
		if (pizzaList.isEmpty()) {
			pizzaId.add(new Long(0));
			return new ResponseEntity<>(pizzaId, HttpStatus.NOT_FOUND);
		} else {
			for (Pizza pizza : pizzaList) {
				pizzaId.add(pizza.getId());
			}
			logger.info("output pizzaId ids");
			return new ResponseEntity<>(pizzaId, HttpStatus.OK);
		}
	}

	/* 
	 * get pizza detail for pizza id
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/pizza/{pizzaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pizza> getPizzaById(@PathVariable("pizzaId") long id) {
		logger.info("inside getPizzaById()");
		logger.debug("pizzaId {}", id);
		Pizza pizzaList = pizzaRepository.findOne(id);
		if (pizzaList == null) {
			logger.info("retruned HttpStatus.NOT_FOUND");
			return new ResponseEntity<Pizza>(pizzaList, HttpStatus.NOT_FOUND);
		} else {
			logger.debug("retruned {} HttpStatus {}", pizzaList, HttpStatus.OK);
			return new ResponseEntity<Pizza>(pizzaList, HttpStatus.OK);
		}
	}

	/*
	 * add new pizza
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/pizza", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pizza> addPizza(@RequestBody PizzaRequest pizzaRequest) {
		logger.info("inside addPizza()");
		logger.debug("Pizza Request({})", pizzaRequest);
		if (pizzaService.isValid(pizzaRequest)) {
			logger.info("pizza request valid");
			Pizza pizza = new Pizza(pizzaRequest.getName(), pizzaRequest.getSize(), pizzaRequest.getPrice());
			pizzaRepository.save(pizza);
			logger.info("pizza saved successfully");
			HttpHeaders headers = new HttpHeaders();
			headers.add("location", "/pizza/" + (pizza.getId()).toString());
			logger.debug("pizza id {} generated", pizza.getId().toString());

			return new ResponseEntity<Pizza>(pizza, headers, HttpStatus.CREATED);
		} else {
			logger.info("Bad Request");
			return new ResponseEntity<Pizza>(pizzaRequest, HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * Update the pizza have {id} with price, size and name
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/pizza/{pizzaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pizza> updatePizza(@PathVariable("pizzaId") long id,
			@RequestBody PizzaRequest pizzaRequest) {
		if (pizzaService.isValid(pizzaRequest)) {
			logger.info("pizza request valid");
			if (pizzaRepository.exists(id)) {
				Pizza pizza = pizzaRepository.findOne(id);
				pizza.setPrice(pizzaRequest.getPrice());
				pizza.setSize(pizzaRequest.getSize());
				pizza.setName(pizzaRequest.getName());
				pizzaRepository.save(pizza);
				logger.info("pizza saved successfully");
				return new ResponseEntity<Pizza>(pizza, HttpStatus.NO_CONTENT);
			} else {
				logger.info("pizza detail not found");
				return new ResponseEntity<Pizza>(pizzaRequest, HttpStatus.NOT_FOUND);
			}
		} else {
			logger.info("Bad Request");
			return new ResponseEntity<Pizza>(pizzaRequest, HttpStatus.BAD_REQUEST);
		}
	}

	/* 
	 * delete pizza detail for pizza id
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/pizza/{pizzaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pizza> deletePizza(@PathVariable("pizzaId") long id) {
		logger.info("inside deletePizza()");
		logger.debug("PizzaId ({})", id);
		Pizza pizzaList = pizzaRepository.findOne(id);
		if (pizzaService.isIdValid(id)) {
			if (pizzaRepository.exists(id)) {
				pizzaRepository.delete(id);
				logger.info("pizza deleted successfully");
				return new ResponseEntity<Pizza>(pizzaList, HttpStatus.NO_CONTENT);
			} else {
				logger.info("pizza detail not found");
				return new ResponseEntity<Pizza>(pizzaList, HttpStatus.NOT_FOUND);
			}
		} else {
			logger.info("Bad Request");
			return new ResponseEntity<Pizza>(pizzaList, HttpStatus.BAD_REQUEST);
		}
	}
}
