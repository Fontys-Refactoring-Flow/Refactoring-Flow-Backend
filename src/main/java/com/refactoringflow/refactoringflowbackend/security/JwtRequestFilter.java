package com.refactoringflow.refactoringflowbackend.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactoringflow.refactoringflowbackend.error.exceptions.ExpiredJwtException;
import com.refactoringflow.refactoringflowbackend.exchanges.ErrorResponse;
import com.refactoringflow.refactoringflowbackend.model.user.User;
import com.refactoringflow.refactoringflowbackend.service.JwtTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;

    public JwtRequestFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {
        String token = extractJwtFromRequest(request);

        try {
            if (StringUtils.hasText(token)) {
                if (!jwtTokenService.validateToken(token)) {
                    ErrorResponse errorResponse = new ErrorResponse(
                            HttpStatus.UNAUTHORIZED,
                            new ExpiredJwtException("The provided JWT has expired"));

                    sendErrorResonse(errorResponse, response);
                    SecurityContextHolder.getContext().setAuthentication(null);
                    return;
                }

                SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
            }
            chain.doFilter(request, response);
            SecurityContextHolder.getContext().setAuthentication(null);
        } catch (JWTVerificationException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.UNAUTHORIZED,
                    e);

            sendErrorResonse(errorResponse, response);
            chain.doFilter(request, response);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }

    private Authentication getAuthentication(String authToken) {
        User user = jwtTokenService.getUserFromToken(authToken);
        return new UsernamePasswordAuthenticationToken(user.getName(), null, user.getAuthorities());
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public void sendErrorResonse(ErrorResponse err, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.writeValue(out, err);
        out.flush();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.equals("/api/v1//login") || path.equals("/api/v1/register")
                || path.equals("/api/v1/refresh") || path.equals("/api/v1/error");
    }
}
