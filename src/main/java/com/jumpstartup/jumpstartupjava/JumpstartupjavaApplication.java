package com.jumpstartup.jumpstartupjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class JumpstartupjavaApplication {

	private static final Logger logger = LoggerFactory.getLogger(JumpstartupjavaApplication.class);

	public static void main(String[] args) {
		logger.info("Starting JumpstartupjavaApplication...");
		SpringApplication.run(JumpstartupjavaApplication.class, args);
		logger.info("JumpstartupjavaApplication started.");
	}

}

