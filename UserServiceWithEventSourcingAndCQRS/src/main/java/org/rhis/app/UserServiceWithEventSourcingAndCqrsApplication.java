package org.rhis.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceWithEventSourcingAndCqrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceWithEventSourcingAndCqrsApplication.class, args);
	}

}
