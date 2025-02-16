package com.axon.userservice.modules.user.service;

import com.axon.userservice.modules.user.dto.UserRequestDTO;
import com.axon.userservice.modules.user.dto.UserResponseDTO;
import com.axon.userservice.modules.user.model.entity.UserEntity;
import com.axon.userservice.modules.user.repository.IUserRepository;
import com.axon.userservice.modules.user.validators.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCommandServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserValidator userValidator;

    @InjectMocks
    private UserCommandService userCommandService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
        user.setNombres("Juan");
        user.setApellidos("Pérez");
        user.setRut(12345678L);
        user.setDv("K");
        user.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        user.setCorreoElectronico("juan.perez@example.com");
        user.setContrasena(passwordEncoder.encode("Secure@123"));
        return user;
    }

    @Test
    void testCreateUser_Success() {
        UserRequestDTO dto = createUserRequestDTO();
        UserEntity savedEntity = createUserEntity();

        doNothing().when(userValidator).validateNewUser(dto);
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedEntity);

        UserResponseDTO response = userCommandService.createUser(dto);

        assertNotNull(response);
        assertEquals(savedEntity.getId(), response.getId());
        assertEquals(savedEntity.getNombres(), response.getNombres());
        verify(userValidator, times(1)).validateNewUser(dto);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testUpdateUser_Success() {
        UserRequestDTO dto = createUserRequestDTO();
        dto.setNombres("Carlos");

        UserEntity existingUser = createUserEntity();
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenReturn(existingUser);

        UserResponseDTO response = userCommandService.updateUser(1L, dto);

        assertNotNull(response);
        assertEquals("Carlos", response.getNombres());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testUpdateUser_NotFound() {
        UserRequestDTO dto = createUserRequestDTO();
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userCommandService.updateUser(1L, dto));
        assertEquals("Usuario no encontrado", exception.getMessage());

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void testDeleteUser_Success() {
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);

        assertDoesNotThrow(() -> userCommandService.deleteUser(1L));

        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteUser_NotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userCommandService.deleteUser(1L));
        assertEquals("Usuario no encontrado", exception.getMessage());

        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, never()).deleteById(anyLong());
    }
}
