package com.axon.userservice.modules.user.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidatorRut.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRut {
    String message() default "RUT inválido, debe tener entre 7 y 8 dígitos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
