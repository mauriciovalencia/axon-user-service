package com.axon.userservice.modules.user.validators;

import com.axon.userservice.modules.user.config.UserConfig;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class ValidatorPassword {

    private static final String PASSWORD_TEMPLATE = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%%*?&])[A-Za-z\\d@$!%%*?&]{%d,}$";
    private final Pattern passwordPattern;

    public ValidatorPassword(UserConfig config) {
        int minLength = config.getPasswordMinLength();

        if (minLength < 1) {
            throw new IllegalArgumentException("El tamaño mínimo de la contraseña debe ser mayor a 0.");
        }

        try {
            String passwordRegex = String.format(PASSWORD_TEMPLATE, minLength);
            this.passwordPattern = Pattern.compile(passwordRegex);
        } catch (Exception e) {
            throw new RuntimeException("Error formateando la regex para la contraseña. Valor de passwordMinLength: " + minLength, e);
        }
    }

    public boolean isValid(String password) {
        return password != null && !password.isEmpty() && passwordPattern.matcher(password).matches();
    }
}
