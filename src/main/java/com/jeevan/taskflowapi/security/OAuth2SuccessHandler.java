package com.jeevan.taskflowapi.security;

import com.jeevan.taskflowapi.entity.RefreshToken;
import com.jeevan.taskflowapi.entity.User;
import com.jeevan.taskflowapi.enums.Role;
import com.jeevan.taskflowapi.repository.UserRepository;
import com.jeevan.taskflowapi.service.JwtService;
import com.jeevan.taskflowapi.service.RefreshTokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {

                    User newUser = User.builder()
                            .fullName(name)
                            .email(email)
                            .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                            .role(Role.ROLE_USER)
                            .enabled(true)
                            .build();

                    return userRepository.save(newUser);

                });

        String accessToken = jwtService.generateToken(user.getEmail());

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);

        response.setContentType("application/json");

        response.getWriter().write("""
{
    "accessToken":"%s",
    "refreshToken":"%s"
}
""".formatted(
                accessToken,
                refreshToken.getToken()
        ));
    }
}