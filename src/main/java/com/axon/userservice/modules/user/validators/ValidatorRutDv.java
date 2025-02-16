package com.axon.userservice.modules.user.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorRutDv implements ConstraintValidator<ValidRutDv, String> {

    @Override
    public boolean isValid(String dv, ConstraintValidatorContext context) {
        if (dv == null || dv.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El dígito verificador no puede estar vacío.")
                    .addConstraintViolation();
            return false;
        }

        dv = dv.toUpperCase(); // 🔹 Convertir a mayúsculas para evitar errores con 'k' en minúscula

        if (!dv.matches("^[0-9K]$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El dígito verificador debe ser un número entre 0-9 o la letra K.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
