package com.app.pizza;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		log .info("Pizza Service is starting");
		SpringApplication.run(Application.class, args);
	}
	
	//To add in database for local testing
//	@Bean
//	CommandLineRunner initDatabase(PizzaRepository repository) {
//		return args -> {
//			log.info("Preloading " + repository.save(new Pizza("Pizza1", Size.Standard, 10.0f)));
//			log.info("Preloading " + repository.save(new Pizza("Pizza2", Size.Large, 20.0f)));
//		};
//	}
}
