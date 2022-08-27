package com.test.securtiy.config;

import com.test.securtiy.filter.JwtAuthenticationFilter;
import com.test.securtiy.jwt.JwtAuthenticationEntryPoint;
import com.test.securtiy.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.PostConstruct;

@EnableWebSecurity
public class JwtConfig {
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @PostConstruct
    public void init(){
        jwtAuthenticationFilter=new JwtAuthenticationFilter(jwtTokenProvider);
        //jwtAuthenticationFilter.setFilterProcessesUrl();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션을 사용하지 않음 JSESSION X
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeHttpRequests().anyRequest().permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider)
                    ,UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);
//                .authorizeRequests(authorize -> authorize
//                        .antMatchers("/jwt/user").access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER')")
//                        .antMatchers("/jwt/manager").access("hasRole('ROLE_MANAGER')")
//                        .anyRequest().permitAll()
//                        .and()
//                        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider)
//                                ,UsernamePasswordAuthenticationFilter.class)
//                )

        return http.build();

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration=new CorsConfiguration();
        configuration.setAllowCredentials(true); //내 서버가 응답할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/jwt/**",configuration);
        return source;
    }
}
