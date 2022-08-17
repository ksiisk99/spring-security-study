package com.test.securtiy.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    //구글로 받은 userRequest 데이터에 대한 후처리 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration: "+userRequest.getClientRegistration());
        System.out.println("getAccessToken: "+userRequest.getAccessToken());

        //구글로그인 -> code를 리턴(OAuth-Client라이브러리가 받음) -> AccessToken요청 -> userRequest 정보를 줌 -> loadUser함수 -> 회원프로필을 받아줌
        System.out.println("getAttributes: "+super.loadUser(userRequest).getAttributes());
        //username=sub아이디 , password = 암호화된 비번 , email = email 으로 회원가입 시킴
        return super.loadUser(userRequest);
    }
}
