package com.test.securtiy.controller;

import com.test.securtiy.auth.PrincipalDetails;
import com.test.securtiy.entity.User;
import com.test.securtiy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping(value = "/manager/good")
    public @ResponseBody String good(){
        return "Good World";
    }


    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }


    @GetMapping("/join")
    public String join(){
        return "joinForm";
    }
    @PostMapping("/join")
    public String join(User user){
        user.setRole("Customer");
        String rawPassword=user.getPassword();
        String encPassword=bCryptPasswordEncoder.encode(rawPassword);
        System.out.println("PASSWORD: "+encPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "redirect:/loginForm";
    }


    @GetMapping("/test/login")
    public @ResponseBody String testLogin(
            Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails){
        //@AuthenticationPrincipal은 세션 정보를 확인할 수 있음
        System.out.println("=========/test/login =========");
        PrincipalDetails principalDetails= (PrincipalDetails) authentication.getDetails();
        System.out.println("authentication: "+principalDetails.getUser());

        System.out.println("userDetails: "+userDetails.getUser());
        return "세션 정보 확인하기";
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOauthLogin(
            Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails){
        System.out.println("=========/test/login =========");
        OAuth2User oAuth2User= (OAuth2User) authentication.getDetails();
        System.out.println("authentication: "+oAuth2User.getAttributes());

        System.out.println("oauth2User: "+userDetails.getAttributes());
        return "Oauth 세션 정보 확인하기";
    }
    @GetMapping("/")
    public @ResponseBody String root(){
        return "success";
    }
}
