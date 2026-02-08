package com.hr.api.application.restapiadapter.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.api.bootstrap.JWTService;

@RestController
public class LoginController {

    private JWTService jwtService;
    private AuthenticationManager authenticationManager;

    public LoginController(JWTService service, AuthenticationManager manager) {
        jwtService = service;
        authenticationManager = manager;
    }


    @PostMapping("/auth/login")
    public String getToken(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), 
                loginRequest.getPassword()
            )
        );
        String token = jwtService.generateToken(authentication);
        return token;
    }
}
