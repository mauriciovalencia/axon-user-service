package com.axon.userservice.modules.user.validators;

import com.axon.userservice.modules.user.config.UserConfig;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.Period;

@Component
public class ValidatorAge implements ConstraintValidator<ValidAge, LocalDate> {

    private int minAge = 18; // Valor por defecto para evitar errores en los tests

    public ValidatorAge() {} // Constructor sin argumentos

    public ValidatorAge(UserConfig config) {
        this.minAge = config.getUserMinAge();
    }

    @Override
    public boolean isValid(LocalDate fechaNacimiento, ConstraintValidatorContext context) {
        if (fechaNacimiento == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("La fecha de nacimiento no puede estar vacía.")
                    .addConstraintViolation();
            return false;
        }

        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= minAge;
    }
}
