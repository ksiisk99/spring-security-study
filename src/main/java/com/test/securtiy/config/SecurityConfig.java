package com.test.securtiy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .antMatcher("/manager/**")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().hasRole("ADMIN")) //권한 체크
                .csrf().disable();
        return http.build();
    }

    @Bean
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/hello").authenticated() //인증 요청
                        .anyRequest().permitAll())  //나머지 요청은 허락
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
