package com.app.pizza;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.pizza.entity.Pizza;
import com.app.pizza.entity.Size;
import com.app.pizza.repository.PizzaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ApplicationHttpRequestTest {

	private static final Logger log = LoggerFactory.getLogger(ApplicationHttpRequestTest.class);

	@LocalServerPort
	private int port;

	private static String API_VER = "/v1";

	private static String PIZZA = "/pizza";

	@Autowired
	static PizzaRepository pizzaRepository;

	@Autowired
	private TestRestTemplate restTemplate;

	@TestConfiguration
	static class Config {
		@Bean
		CommandLineRunner initDatabase(PizzaRepository repository) {
			return args -> {
				log.info("Preloading " + repository.save(new Pizza("Pizza1", Size.Standard, 10.0f)));
				log.info("Preloading " + repository.save(new Pizza("Pizza2", Size.Large, 20.0f)));
			};
		}
	}

	@Test
	public void testGetPizzaRequest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + API_VER + PIZZA, String.class))
				.contains("[1,2]");
	}
}