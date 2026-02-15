package com.hr.api.application.restapiadapter.login;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.api.bootstrap.CustomUserDetailsService;
import com.hr.api.bootstrap.JWTService;

@RestController
public class LoginController {

    private JWTService jwtService;
    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService customUserDetailsService;

    public LoginController(JWTService service, AuthenticationManager manager, CustomUserDetailsService customUserDetailsService) {
        jwtService = service;
        authenticationManager = manager;
        this.customUserDetailsService = customUserDetailsService;
    }


    @PostMapping("/auth/login")
    public String getToken(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), 
                loginRequest.getPassword()
            )
        );

        String token = jwtService.generateToken(
            customUserDetailsService.loadUserByUsername(
                loginRequest.getUsername()
            )
        );
        return token;
    }
}
