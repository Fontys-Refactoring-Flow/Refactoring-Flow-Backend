package com.refactoringflow.refactoringflowbackend.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactoringflow.refactoringflowbackend.exchanges.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class RestForbiddenEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        if(authException instanceof OAuth2AuthenticationException) {
            OAuth2Error error = ((OAuth2AuthenticationException) authException).getError();
            if(error instanceof BearerTokenError bearerError) {
                new BearerTokenAuthenticationEntryPoint().commence(request, response, authException);
                mapper.writeValue(out, new ErrorResponse(bearerError.getHttpStatus(),
                        bearerError.getErrorCode() + ": " + bearerError.getDescription()));
            }
        } else {
            mapper.writeValue(out, new ErrorResponse(HttpStatus.UNAUTHORIZED,
        "You are not authorized to access this resource."));
        }
        out.flush();
    }

}