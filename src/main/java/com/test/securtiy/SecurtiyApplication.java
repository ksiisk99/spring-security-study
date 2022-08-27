package com.test.securtiy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

//exclude={DataSourceAutoConfiguration.class}
@EnableJpaAuditing //BaseEntity 실행
@SpringBootApplication
public class SecurtiyApplication {
	private static final Logger log= LoggerFactory.getLogger(SecurtiyApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SecurtiyApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder encodePwd(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuditorAware<String> auditorProvider(){
		return new AuditorAware<String>() {
			@Override
			public Optional<String> getCurrentAuditor() {
				return Optional.of(new SimpleDateFormat("yy-MM-dd")
						.format(new Date()));
			}
		};
	}
}
