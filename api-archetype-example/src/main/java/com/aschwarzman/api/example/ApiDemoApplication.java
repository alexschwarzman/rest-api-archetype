package com.aschwarzman.api.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApiDemoApplication.class);
		app.run(args);
	}

}
