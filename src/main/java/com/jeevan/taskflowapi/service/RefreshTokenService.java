package com.jeevan.taskflowapi.service;

import com.jeevan.taskflowapi.dto.response.LoginResponse;
import com.jeevan.taskflowapi.entity.RefreshToken;
import com.jeevan.taskflowapi.entity.User;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    LoginResponse refreshToken(String token);

    void deleteRefreshToken(User user);

}