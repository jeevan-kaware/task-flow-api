package com.jeevan.taskflowapi.service.impl;

import com.jeevan.taskflowapi.dto.response.LoginResponse;
import com.jeevan.taskflowapi.entity.RefreshToken;
import com.jeevan.taskflowapi.entity.User;
import com.jeevan.taskflowapi.exception.BadRequestException;
import com.jeevan.taskflowapi.repository.RefreshTokenRepository;
import com.jeevan.taskflowapi.service.JwtService;
import com.jeevan.taskflowapi.service.RefreshTokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    @Override
    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByUser(user)
                .orElse(new RefreshToken());

        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(7));

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public LoginResponse refreshToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(token)
                .orElseThrow(() -> new BadRequestException("Refresh Token not found"));

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new BadRequestException("Refresh Token expired");
        }

        String accessToken =
                jwtService.generateToken(refreshToken.getUser().getEmail());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    @Override
    public void deleteRefreshToken(User user) {
        refreshTokenRepository.deleteByUser(user);
    }
}