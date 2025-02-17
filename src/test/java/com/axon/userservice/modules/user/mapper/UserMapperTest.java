package com.axon.userservice.modules.user.mapper;

import com.axon.userservice.modules.user.dto.UserRequestDTO;
import com.axon.userservice.modules.user.dto.UserResponseDTO;
import com.axon.userservice.modules.user.model.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UserRequestDTO createUserRequestDTO() {
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

    private UserEntity createUserEntity() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setNombres("Maria");
        user.setApellidos("González");
        user.setRut(87654321L);
        user.setDv("J");
        user.setFechaNacimiento(LocalDate.of(1985, 5, 15));
        user.setCorreoElectronico("maria.gonzalez@example.com");
        user.setContrasena(passwordEncoder.encode("Secure@123"));
        return user;
    }

    @Test
    void testToEntity_Success() {
        UserRequestDTO dto = createUserRequestDTO();
        UserEntity entity = UserMapper.toEntity(dto);

        assertNotNull(entity, "La entidad no debe ser nula");
        assertEquals(dto.getNombres(), entity.getNombres());
        assertEquals(dto.getApellidos(), entity.getApellidos());
        assertEquals(dto.getRut(), entity.getRut());
        assertEquals(dto.getDv(), entity.getDv());
        assertEquals(dto.getFechaNacimiento(), entity.getFechaNacimiento());
        assertEquals(dto.getCorreoElectronico(), entity.getCorreoElectronico());
        assertTrue(passwordEncoder.matches(dto.getContrasena(), entity.getContrasena()), "La contraseña debe estar encriptada correctamente");
    }

    @Test
    void testToEntity_NullDTO_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserMapper.toEntity(null);
        });
        assertEquals("El DTO no puede ser nulo", exception.getMessage());
    }

    @Test
    void testToResponseDTO_Success() {
        UserEntity entity = createUserEntity();
        UserResponseDTO responseDTO = UserMapper.toResponseDTO(entity);

        assertNotNull(responseDTO, "El DTO de respuesta no debe ser nulo");
        assertEquals(entity.getId(), responseDTO.getId());
        assertEquals(entity.getNombres(), responseDTO.getNombres());
        assertEquals(entity.getApellidos(), responseDTO.getApellidos());
        assertEquals(entity.getRut(), responseDTO.getRut());
        assertEquals(entity.getDv(), responseDTO.getDv());
        assertEquals(entity.getFechaNacimiento(), responseDTO.getFechaNacimiento());
        assertEquals(entity.getCorreoElectronico(), responseDTO.getCorreoElectronico());
    }

    @Test
    void testToResponseDTO_NullEntity_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UserMapper.toResponseDTO(null);
        });
        assertEquals("La entidad no puede ser nula", exception.getMessage());
    }
}
