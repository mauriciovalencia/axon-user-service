package com.axon.userservice.modules.user.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ValidatorAge.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAge {
    String message() default "El usuario debe ser mayor de 18 años";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}