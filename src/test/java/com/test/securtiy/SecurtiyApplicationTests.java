package com.test.securtiy;

import com.test.securtiy.entity.User;
import com.test.securtiy.jwt.JwtTokenProvider;
import com.test.securtiy.repository.UserRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
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
//	JwtTokenProvider jwtTokenProvider;
	@Test
	void jwtTest() {
		User user=new User(123L,"ABC","ABC","ABC");
		userRepository.save(user);

//		User user2=new User(321L,"ABC","ABC","ABC");
//		userRepository.save(user2);
		System.out.println(userRepository.findById(123L).get().getCreatedDate());
		user.setUsername("BCA");
		userRepository.save(user);
		System.out.println(userRepository.findById(123L).get().getLastModifiedDate());
	}

}
