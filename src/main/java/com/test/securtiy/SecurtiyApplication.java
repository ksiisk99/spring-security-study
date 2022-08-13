package com.test.securtiy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//exclude={DataSourceAutoConfiguration.class}
@SpringBootApplication
public class SecurtiyApplication {
	private static final Logger log= LoggerFactory.getLogger(SecurtiyApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SecurtiyApplication.class, args);
		log.info("Hello info");
	}

}
