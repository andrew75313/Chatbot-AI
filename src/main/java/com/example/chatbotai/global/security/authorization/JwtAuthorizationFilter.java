package com.example.chatbotai.global.security.authorization;

import com.example.chatbotai.global.security.handler.JwtAccessDeniedHandler;
import com.example.chatbotai.global.security.jwt.JwtUtil;
import com.example.chatbotai.global.security.user.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {

        try {
            String token = jwtUtil.getJwtFromHeader(request);

            if (StringUtils.hasText(token) && jwtUtil.validateToken(token)) {
                Claims claims = jwtUtil.getClaimFromToken(token);
                setAuthentication(claims.getSubject());
            }

            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            authenticationEntryPoint.commence(request, response, new AuthenticationException("JWT 인증 실패") {
            });
        } catch (AccessDeniedException e) {
            accessDeniedHandler.handle(request, response, new AccessDeniedException("권한이 거부 되었습니다."));
        } catch (Exception e) {
            throw new RuntimeException("JWT 인증 요청 실패");
        }
    }

    private void setAuthentication(String userId) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(userId);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private Authentication createAuthentication(String userId) {
        UserDetails userDetails = userDetailsService.loadUserByUserId(userId);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
