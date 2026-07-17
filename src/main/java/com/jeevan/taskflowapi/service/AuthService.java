package com.jeevan.taskflowapi.service;

import com.jeevan.taskflowapi.dto.request.LoginRequest;
import com.jeevan.taskflowapi.dto.request.RegisterRequest;
import com.jeevan.taskflowapi.dto.response.LoginResponse;
import com.jeevan.taskflowapi.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
    void logout(String refreshToken);
}