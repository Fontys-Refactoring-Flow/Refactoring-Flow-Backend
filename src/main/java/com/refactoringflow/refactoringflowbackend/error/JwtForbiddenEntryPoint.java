package com.refactoringflow.refactoringflowbackend.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactoringflow.refactoringflowbackend.exchanges.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class JwtForbiddenEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        if(authException instanceof OAuth2AuthenticationException) {
            OAuth2Error error = ((OAuth2AuthenticationException) authException).getError();
            if(error instanceof BearerTokenError bearerTokenError) {
                mapper.writeValue(out, new ErrorResponse(HttpStatus.UNAUTHORIZED,
                    bearerTokenError.getDescription()));
                response.setStatus(bearerTokenError.getHttpStatus().value());
            }
        }
        out.flush();
    }

}