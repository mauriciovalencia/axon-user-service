package com.axon.userservice.modules.user.dto;

import com.axon.userservice.modules.user.config.UserConfig;
import com.axon.userservice.modules.user.validators.ValidatorAge;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private UserRequestDTO createValidUser() {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setNombres("Juan");
        dto.setApellidos("Pérez");
        dto.setRut(12345678L);
        dto.setDv("K");
        dto.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        dto.setCorreoElectronico("juan.perez@example.com");
        dto.setContrasena("Secure@123");
        return dto;
    }

    @Test
    void testValidUser() {
        UserRequestDTO dto = createValidUser();

        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "El usuario válido no debería tener violaciones de validación");
    }

    @Test
    void testInvalidFechaNacimiento() {
        UserRequestDTO dto = createValidUser();
        dto.setFechaNacimiento(LocalDate.now().plusDays(1)); // Fecha de nacimiento en el futuro

        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Debe fallar la validación para fecha de nacimiento futura");
    }
}
