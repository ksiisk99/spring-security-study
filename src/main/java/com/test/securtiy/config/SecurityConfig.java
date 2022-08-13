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
                .cors().and().httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/").authenticated() //인증 요청
                        .anyRequest().permitAll())  //나머지 요청은 허락
                .formLogin(page -> page.loginPage("/loginForm") //커스텀 로그인 페이지
                        .loginProcessingUrl("/login") //login주소가 호출이 되면 시큐리티가 대신 로그인
                        .defaultSuccessUrl("/"));
                //usernameParameter or passwordParameter로 변수명 변경할 수 있음
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }
}
