package com.ipd.eduplus.controller;

import com.ipd.eduplus.domain.dtos.AuthResponse;
import com.ipd.eduplus.domain.dtos.LoginRequest;
import com.ipd.eduplus.domain.dtos.RefreshTokenRequest;
import com.ipd.eduplus.domain.dtos.RegisterRequest;
import com.ipd.eduplus.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest request) {
        authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh-token")
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }
}