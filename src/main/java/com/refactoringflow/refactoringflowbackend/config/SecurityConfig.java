package com.refactoringflow.refactoringflowbackend.config;

import com.refactoringflow.refactoringflowbackend.error.RestAccessDeniedHandler;
import com.refactoringflow.refactoringflowbackend.error.RestForbiddenEntryPoint;
import com.refactoringflow.refactoringflowbackend.model.Role;
import com.refactoringflow.refactoringflowbackend.service.RoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@Component
public class SecurityConfig {
    public static final String AUTHORITIES_CLAIM_NAME = "roles";
    public static Role ROLE_STUDENT;
    public static Role ROLE_TEACHER;

    public final JwtDecoder jwtDecoder;

    public SecurityConfig(JwtDecoder jwtDecoder, RoleService roleService) {
        this.jwtDecoder = jwtDecoder;

        ROLE_STUDENT = roleService.findByName("STUDENT");
        ROLE_TEACHER = roleService.findByName("TEACHER");
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new RestAccessDeniedHandler())
                .authenticationEntryPoint(new RestForbiddenEntryPoint())
                .and()
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(authenticationConverter())
                .and()
                .accessDeniedHandler(new RestAccessDeniedHandler())
                .authenticationEntryPoint(new RestForbiddenEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/api/v1/error",
                        "/api/v1/login",
                        "/api/v1/register",
                        "/api/v1/refresh"
                )
                .permitAll()
                .anyRequest()
                .hasAuthority("STUDENT")
                .and().build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(
                Arrays.asList(
                        "GET",
                        "POST",
                        "DELETE",
                        "PUT",
                        "PATCH"
                ));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    protected JwtAuthenticationConverter authenticationConverter() {
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("");
        authoritiesConverter.setAuthoritiesClaimName(AUTHORITIES_CLAIM_NAME);

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return converter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
