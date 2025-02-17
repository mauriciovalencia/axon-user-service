package com.axon.userservice.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    @Bean
    public String userServiceApiKey() {
        return dotenv.get("USER_SERVICE_API_KEY", "default_value");
    }

    @Bean
    public String allowedOrigins() {
        return dotenv.get("CORS_ALLOWED_ORIGINS", "*");
    }

    @Bean
    public String allowedMethods() {
        return dotenv.get("CORS_ALLOWED_METHODS", "GET,POST,PUT,DELETE");
    }


    @Bean
    public int passwordMinLength() {
        return Integer.parseInt(dotenv.get("USER_MODULE_PASSWORD_MIN_LENGTH", "8"));
    }

    @Bean
    public int userMinAge() {
        return Integer.parseInt(dotenv.get("USER_MODULE_MIN_AGE", "18"));
    }
}
