package com.axon.userservice.modules.user.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorRut implements ConstraintValidator<ValidRut, Long> {

    @Override
    public boolean isValid(Long rut, ConstraintValidatorContext context) {
        if (rut == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El RUT no puede estar vacío.")
                    .addConstraintViolation();
            return false;
        }

        String rutStr = String.valueOf(rut);
        if (rutStr.length() < 7 || rutStr.length() > 8) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El RUT debe tener entre 7 y 8 dígitos.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
