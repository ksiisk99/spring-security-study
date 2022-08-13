package com.test.securtiy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .cors().and()
                .antMatcher("/manager/**")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().hasRole("ADMIN")) //권한 체크
                .csrf().disable();
        return http.build();
    }

    @Bean
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/hello").authenticated() //인증 요청
                        .anyRequest().permitAll())  //나머지 요청은 허락
                .formLogin(page -> page.loginPage("/login")); //커스텀 로그인 페이지
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }
}
