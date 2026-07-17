package com.jeevan.taskflowapi.controller;

import com.jeevan.taskflowapi.dto.request.RefreshTokenRequest;
import com.jeevan.taskflowapi.dto.response.LoginResponse;
import com.jeevan.taskflowapi.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(
            @Valid
            @RequestBody RefreshTokenRequest request) {

        return ResponseEntity.ok(
                refreshTokenService.refreshToken(
                        request.getRefreshToken()
                )
        );
    }
}