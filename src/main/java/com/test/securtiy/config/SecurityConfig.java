//package com.test.securtiy.config;
//
//import com.test.securtiy.oauth.PrincipalOauth2UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SecurityConfig {
//    @Autowired
//    private PrincipalOauth2UserService principalOauth2UserService;
//
//    @Bean
//    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//        http
//                .httpBasic(Customizer.withDefaults())
//                .cors().and()
//                .csrf().disable()
//                .antMatcher("/manager/**")
//                .authorizeHttpRequests(authorize -> authorize.anyRequest().hasRole("ROLE_USER")); //권한 체크
//        return http.build();
//    }
//
//    @Bean
//    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors().and()
//                .httpBasic(Customizer.withDefaults())
//                .csrf().disable()
//                .authorizeHttpRequests(authorize -> authorize
//                        .antMatchers("/").authenticated() //인증 요청
//                        .anyRequest().permitAll())  //나머지 요청은 허락
//                .formLogin(page -> page.loginPage("/loginForm") //커스텀 로그인 페이지
//                        .loginProcessingUrl("/login") //login주소가 호출이 되면 시큐리티가 대신 로그인
//                        .defaultSuccessUrl("/"))
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(principalOauth2UserService); //구글 로그인이 완료된 뒤의 후처리가 필요함. => 액세스토큰 + 사용자 프로필정보(o)
//                //usernameParameter or passwordParameter로 변수명 변경할 수 있음
//        return http.build();
//    }
//
//
//}
