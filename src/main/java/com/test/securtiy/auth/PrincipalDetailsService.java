//package com.test.securtiy.auth;
//
//import com.test.securtiy.entity.User;
//import com.test.securtiy.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
////시큐리티 설정에서 loginProcessingUrl("/login")
////login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUserName 함수가 실행
//@Service
//public class PrincipalDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user=userRepository.findByUsername(username);
//        System.out.println("USERNAME: "+user.getUsername());
//        if(user!=null){
//            return new PrincipalDetails(user);
//            //return되면 Authentication 내부에 UserDetails가 들어가고
//            //Authentication은 시큐리티 세션에 들어간다. loadUserByUsername이 알아서 이 작업을 해준다.
//            //loadUserByUsername은 자동으로 실행되는데 재정의하는 이유는 사용자 정의 PrincipalDetails를 return해주기 위함이다.
//        }
//        return null;
//    }
//}
