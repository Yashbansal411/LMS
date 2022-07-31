package com.lms.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserMicroserviceApplication {
	static Logger logger = LoggerFactory.getLogger(UserMicroserviceApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(UserMicroserviceApplication.class, args);
		logger.info("application run successfully");
	}

}
