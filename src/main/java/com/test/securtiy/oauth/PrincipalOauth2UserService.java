package com.test.securtiy.oauth;

import com.test.securtiy.auth.PrincipalDetails;
import com.test.securtiy.entity.User;
import com.test.securtiy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    //구글로 받은 userRequest 데이터에 대한 후처리 함수
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration: "+userRequest.getClientRegistration());
        System.out.println("getAccessToken: "+userRequest.getAccessToken());

        OAuth2User oAuth2User=super.loadUser(userRequest);
        //구글로그인 -> code를 리턴(OAuth-Client라이브러리가 받음) -> AccessToken요청 -> userRequest 정보를 줌 -> loadUser함수 -> 회원프로필을 받아줌
        System.out.println("getAttributes: "+super.loadUser(userRequest).getAttributes());
        //username=sub아이디 , password = 암호화된 비번 , email = email 으로 회원가입 시킴

        String provider=userRequest.getClientRegistration().getRegistrationId(); //google
        System.out.println("provider: "+provider);
        String providerId=oAuth2User.getAttribute("sub");
        String username=provider+"_"+providerId; //google_10231259182519255
        String password=bCryptPasswordEncoder.encode("Sangin");
        String email=oAuth2User.getAttribute("email");
        String role="ROLE_USER";

        User userEntity=userRepository.findByUsername(username);
        if(userEntity==null){
            userEntity=User.builder()
                    .username(username)
                    .password(password)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
        }
        return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
    }
}
