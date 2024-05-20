package com.anil.SpringSecurityJWT.controller;

import com.anil.SpringSecurityJWT.model.AuthenticationResponse;
import com.anil.SpringSecurityJWT.model.User;
import com.anil.SpringSecurityJWT.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>register(@RequestBody User request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse>login(@RequestBody User request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}
