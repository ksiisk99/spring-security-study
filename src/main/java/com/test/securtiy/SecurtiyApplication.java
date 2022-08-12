package com.test.securtiy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//exclude={DataSourceAutoConfiguration.class}
@SpringBootApplication
public class SecurtiyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurtiyApplication.class, args);
	}

}
