package com.axon.userservice.modules.user.model.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    void testConstructorAndDefaultValues() {
        UserEntity user = new UserEntity();

        assertNotNull(user.getCreatedAt(), "La fecha de creación no debe ser nula");
        assertNotNull(user.getUpdatedAt(), "La fecha de actualización no debe ser nula");

        // Aseguramos que las fechas son casi iguales (diferencia mínima)
        long difference = Math.abs(user.getCreatedAt().getNano() - user.getUpdatedAt().getNano());
        assertTrue(difference < 1_000_000, "Las fechas de creación y actualización deben ser prácticamente iguales");

        assertEquals(0, user.getVersion(), "La versión inicial debe ser 0");
    }

    @Test
    void testSettersAndGetters() {
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        LocalDateTime now = LocalDateTime.now();

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setNombres("Juan");
        user.setApellidos("Pérez");
        user.setRut(12345678L);
        user.setDv("K");
        user.setFechaNacimiento(birthDate);
        user.setCorreoElectronico("juan.perez@example.com");
        user.setContrasena("Secure@123");
        user.setVersion(2);

        assertEquals(1L, user.getId());
        assertEquals("Juan", user.getNombres());
        assertEquals("Pérez", user.getApellidos());
        assertEquals(12345678L, user.getRut());
        assertEquals("K", user.getDv());
        assertEquals(birthDate, user.getFechaNacimiento());
        assertEquals("juan.perez@example.com", user.getCorreoElectronico());
        assertEquals("Secure@123", user.getContrasena());
        assertEquals(2, user.getVersion());
    }

    @Test
    void testPreUpdate() {
        UserEntity user = new UserEntity();
        LocalDateTime beforeUpdate = user.getUpdatedAt();

        // Simula una actualización
        user.onUpdate();
        LocalDateTime afterUpdate = user.getUpdatedAt();

        assertNotNull(afterUpdate, "La fecha de actualización no debe ser nula");
        assertTrue(afterUpdate.isAfter(beforeUpdate), "La fecha de actualización debe haber cambiado");
    }
}
