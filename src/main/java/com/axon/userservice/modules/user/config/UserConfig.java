package com.axon.userservice.modules.user.config;

import com.axon.userservice.config.EnvConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    private final int passwordMinLength;
    private final int userMinAge;

    public UserConfig(EnvConfig envConfig) {
        this.passwordMinLength = envConfig.passwordMinLength();
        this.userMinAge = envConfig.userMinAge();
    }

    public int getPasswordMinLength() {
        return passwordMinLength;
    }

    public int getUserMinAge() {
        return userMinAge;
    }
}
