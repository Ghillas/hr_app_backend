package com.hr.api.application.restapiadapter.login;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.api.bootstrap.CustomUserDetailsService;
import com.hr.api.bootstrap.JWTService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

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
    public ResponseEntity<?> getToken(
        @RequestBody LoginRequest loginRequest,
        HttpServletResponse response
    ) {
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

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // TODO : Try with secure set as true
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1 heure

        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/auth/user")
    public ResponseEntity<?> userInfo(@CookieValue(value = "token", required = false) String token) {
        if (token == null) {
            return ResponseEntity.status(401).build();
        }
        String username = jwtService.extractUsername(token);
        return ResponseEntity.ok(Map.of("username", username));
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }


}
