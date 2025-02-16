package com.axon.userservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Value("${user_service.api-key}")
    private String userServiceApiKey;

    @Value("${cors.allowed-origins}")
    private String allowedOrigins;

    @Value("${cors.allowed-methods}")
    private String allowedMethods;

    @Bean
    public ApiKeyAuthenticationFilter apiKeyAuthenticationFilter() {
        return new ApiKeyAuthenticationFilter(userServiceApiKey);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS configuration
                .csrf().disable()  // Disable CSRF
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/public/**").permitAll() // Allow public routes
                        .requestMatchers("/users/**").authenticated() // Authentication required for /users
                        .anyRequest().authenticated() // Requires authentication
                )
                .addFilterBefore(apiKeyAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) // API Key filter
                .formLogin().disable() // Disable form login
                .httpBasic().disable() // Disable HTTP basic authentication
                .logout().disable(); // Disable logout

        return http.build();
    }

    // Define CORS correctly
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(allowedOrigins.split(","))); // Allow multiple origins
        config.setAllowedMethods(List.of(allowedMethods.split(","))); // Allowed methods
        config.setAllowedHeaders(List.of("*")); // Allow all headers
        config.setAllowCredentials(true); // Allow credentials
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    // Prevent Spring from creating a default UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> null; // Return null to avoid user-based authentication
    }
}