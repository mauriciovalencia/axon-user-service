package com.axon.userservice.modules.user.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidatorRutDv.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRutDv {
    String message() default "Dígito Verificador inválido, debe ser un número (0-9) o la letra K";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
