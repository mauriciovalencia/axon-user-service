package com.axon.userservice.modules.user.service;

import com.axon.userservice.modules.user.dto.UserResponseDTO;
import com.axon.userservice.modules.user.model.entity.UserEntity;
import com.axon.userservice.modules.user.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserQueryServiceTest {

    @Mock
    private IUserRepository repository;

    @InjectMocks
    private UserQueryService userQueryService;

    private UserEntity createUser(Long id, String nombre, String apellido) {
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setNombres(nombre);
        user.setApellidos(apellido);
        user.setRut(12345678L);
        user.setDv("K");
        user.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        user.setCorreoElectronico(nombre.toLowerCase() + ".perez@example.com");
        return user;
    }

    @Test
    void testGetAllUsers_Success() {
        UserEntity user1 = createUser(1L, "Juan", "Pérez");
        UserEntity user2 = createUser(2L, "Maria", "González");

        when(repository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserResponseDTO> users = userQueryService.getAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("Juan", users.get(0).getNombres());
        assertEquals("Maria", users.get(1).getNombres());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetUserById_Success() {
        UserEntity user = createUser(1L, "Carlos", "Ramírez");

        when(repository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDTO response = userQueryService.getUserById(1L);

        assertNotNull(response);
        assertEquals("Carlos", response.getNombres());

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetUserById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userQueryService.getUserById(1L));
        assertEquals("Usuario no encontrado", exception.getMessage());

        verify(repository, times(1)).findById(1L);
    }
}
