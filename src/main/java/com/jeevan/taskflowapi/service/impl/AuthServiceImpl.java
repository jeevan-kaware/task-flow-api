package com.jeevan.taskflowapi.service.impl;


import com.jeevan.taskflowapi.dto.request.LoginRequest;
import com.jeevan.taskflowapi.dto.request.RegisterRequest;
import com.jeevan.taskflowapi.dto.response.LoginResponse;
import com.jeevan.taskflowapi.dto.response.RegisterResponse;
import com.jeevan.taskflowapi.entity.RefreshToken;
import com.jeevan.taskflowapi.entity.User;
import com.jeevan.taskflowapi.enums.Role;
import com.jeevan.taskflowapi.exception.DuplicateResourceException;
import com.jeevan.taskflowapi.exception.ResourceNotFoundException;
import com.jeevan.taskflowapi.repository.RefreshTokenRepository;
import com.jeevan.taskflowapi.repository.UserRepository;
import com.jeevan.taskflowapi.service.AuthService;
import com.jeevan.taskflowapi.service.JwtService;

import com.jeevan.taskflowapi.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .enabled(true)
                .build();

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .message("Registration completed successfully")
                .build();
    }





    @Override
    public LoginResponse login(LoginRequest request) {


        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()

                )

        );



        User user =
                userRepository.findByEmail(request.getEmail())

                        .orElseThrow(() ->
                                new ResourceNotFoundException("User not found"));



        String accessToken =
                jwtService.generateToken(user.getEmail());

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .build();

    }
    @Override
    public void logout(String refreshToken) {

        RefreshToken token = refreshTokenRepository
                .findByToken(refreshToken)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Refresh Token not found"));

        refreshTokenRepository.delete(token);

    }

}