//package com.test.securtiy.config;
//
//import com.test.securtiy.filter.MyFilter1;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public FilterRegistrationBean<MyFilter1> filter1(){
//        FilterRegistrationBean<MyFilter1>bean =new FilterRegistrationBean<>();
//        bean.addUrlPatterns("/*");
//        bean.setOrder(0);//제일 먼저 필터링함 하지만 시큐리티 필터에서 거는게 더 먼저 필터하기 때문에 순서를 잘 정해야함
//        return bean;
//    }
//}
