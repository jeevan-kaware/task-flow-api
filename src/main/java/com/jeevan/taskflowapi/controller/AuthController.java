package com.jeevan.taskflowapi.controller;


import com.jeevan.taskflowapi.dto.request.LoginRequest;
import com.jeevan.taskflowapi.dto.request.LogoutRequest;
import com.jeevan.taskflowapi.dto.request.RegisterRequest;
import com.jeevan.taskflowapi.dto.response.LoginResponse;
import com.jeevan.taskflowapi.dto.response.RegisterResponse;
import com.jeevan.taskflowapi.service.AuthService;


import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;



    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {

        return ResponseEntity.ok(
                authService.register(request)
        );
    }





    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ){

        return ResponseEntity.ok(
                authService.login(request)
        );

    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(

            @Valid
            @RequestBody
            LogoutRequest request

    ) {

        authService.logout(
                request.getRefreshToken()
        );

        return ResponseEntity.ok(
                "Logout successful"
        );

    }


}