package com.anil.springdockerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDockerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDockerDemoApplication.class, args);

		System.out.println("Boot Started...");
	}

}
