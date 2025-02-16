package com.axon.userservice.modules.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    private final int passwordMinLength;
    private final int userMinAge;

    public UserConfig(
            @Value("${user_module.password-min-length:8}") int passwordMinLength,
            @Value("${user_module.min-age:18}") int userMinAge
    ) {
        this.passwordMinLength = passwordMinLength;
        this.userMinAge = userMinAge;
    }

    public int getPasswordMinLength() {
        return passwordMinLength;
    }

    public int getUserMinAge() {
        return userMinAge;
    }
}
