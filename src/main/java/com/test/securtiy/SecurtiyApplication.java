package com.test.securtiy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//exclude={DataSourceAutoConfiguration.class}
@EnableJpaAuditing //BaseEntity 실행
@SpringBootApplication
public class SecurtiyApplication {
	private static final Logger log= LoggerFactory.getLogger(SecurtiyApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SecurtiyApplication.class, args);
	}

}
