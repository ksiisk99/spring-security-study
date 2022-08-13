package com.test.securtiy.controller;

import com.test.securtiy.entity.User;
import com.test.securtiy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


    @GetMapping("/login")
    public String login(){
        return "loginForm";
    }


    @GetMapping("/join")
    public String join(){
        return "joinForm";
    }
    @PostMapping("/join")
    public String join(User user){
        System.out.println(user);
        user.setRole("Customer");
        String rawPassword=user.getPassword();
        String encPassword=bCryptPasswordEncoder.encode(rawPassword);
        System.out.println(encPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "redirect:/login";
    }

}
