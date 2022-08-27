package com.test.securtiy;

import com.test.securtiy.entity.User;
import com.test.securtiy.jwt.JwtTokenProvider;
import com.test.securtiy.repository.UserRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;

@SpringBootTest
class SecurtiyApplicationTests {
	@Autowired
	UserRepository userRepository;
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	@Test
	void jwtTest() {
		String pattern="/get/.*";
		Assertions.assertTrue("/get/".matches(pattern));
	}

}
