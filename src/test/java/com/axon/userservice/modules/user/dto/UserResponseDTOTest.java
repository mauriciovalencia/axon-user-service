package com.axon.userservice.modules.user.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseDTOTest {

    @Test
    void testConstructorAndGetters() {
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        UserResponseDTO user = new UserResponseDTO(1L, "Juan", "Pérez", 12345678L, "K", birthDate, "juan.perez@example.com");

        assertEquals(1L, user.getId());
        assertEquals("Juan", user.getNombres());
        assertEquals("Pérez", user.getApellidos());
        assertEquals(12345678L, user.getRut());
        assertEquals("K", user.getDv());
        assertEquals(birthDate, user.getFechaNacimiento());
        assertEquals("juan.perez@example.com", user.getCorreoElectronico());
    }

    @Test
    void testSetters() {
        LocalDate birthDate = LocalDate.of(1985, 5, 15);
        UserResponseDTO user = new UserResponseDTO();

        user.setId(2L);
        user.setNombres("Maria");
        user.setApellidos("González");
        user.setRut(87654321L);
        user.setDv("J");
        user.setFechaNacimiento(birthDate);
        user.setCorreoElectronico("maria.gonzalez@example.com");

        assertEquals(2L, user.getId());
        assertEquals("Maria", user.getNombres());
        assertEquals("González", user.getApellidos());
        assertEquals(87654321L, user.getRut());
        assertEquals("J", user.getDv());
        assertEquals(birthDate, user.getFechaNacimiento());
        assertEquals("maria.gonzalez@example.com", user.getCorreoElectronico());
    }

    @Test
    void testToString() {
        LocalDate birthDate = LocalDate.of(1995, 3, 10);
        UserResponseDTO user = new UserResponseDTO(3L, "Carlos", "Ramírez", 12312312L, "L", birthDate, "carlos.ramirez@example.com");

        String expectedString = "UserResponseDTO{id=3, nombres='Carlos', apellidos='Ramírez', rut=12312312, dv='L', fechaNacimiento=1995-03-10, correoElectronico='carlos.ramirez@example.com'}";

        assertEquals(expectedString, user.toString());
    }
}
