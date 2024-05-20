package com.anil.SpringSecurityJWT.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Secured end points by JWT Authentication

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello From HomeController";
    }

    @GetMapping("/adminOnly")
    public String adminOnly(){
        return "Admin From HomeController";
    }
}
