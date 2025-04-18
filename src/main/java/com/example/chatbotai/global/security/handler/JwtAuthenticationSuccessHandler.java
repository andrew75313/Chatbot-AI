package com.example.chatbotai.global.security.handler;

import com.example.chatbotai.domain.users.entity.User;
import com.example.chatbotai.global.security.jwt.JwtUtil;
import com.example.chatbotai.global.security.user.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        String accessToken = jwtUtil.createAccessToken(user);

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        String json = new ObjectMapper().writeValueAsString(
                Map.of("msg", "로그인 성공")
        );

        response.getWriter().write(json);
    }
}