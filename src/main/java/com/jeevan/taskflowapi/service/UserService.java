package com.jeevan.taskflowapi.service;

import com.jeevan.taskflowapi.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

}