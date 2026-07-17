package com.jeevan.taskflowapi.repository;

import com.jeevan.taskflowapi.entity.RefreshToken;
import com.jeevan.taskflowapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUser(User user);

    void deleteByUser(User user);

}