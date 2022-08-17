package com.test.securtiy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/jwt")
public class JwtController {

    @GetMapping("/home")
    public String home(){
        return "<h1>home</h1>";
    }
}
