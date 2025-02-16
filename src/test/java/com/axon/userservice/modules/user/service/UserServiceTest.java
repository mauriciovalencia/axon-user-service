package com.axon.userservice.modules.user.service;

import com.axon.userservice.modules.user.dto.UserRequestDTO;
import com.axon.userservice.modules.user.dto.UserResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private IUserQueryService userQueryService;

    @Mock
    private IUserCommandService userCommandService;

    @InjectMocks
    private UserService userService;

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

    private UserResponseDTO createUserResponseDTO(Long id, String nombre, String apellido) {
        return new UserResponseDTO(id, nombre, apellido, 12345678L, "K", LocalDate.of(1990, 1, 1), nombre.toLowerCase() + ".perez@example.com");
    }

    @Test
    void testGetAllUsers_Success() {
        UserResponseDTO user1 = createUserResponseDTO(1L, "Juan", "Pérez");
        UserResponseDTO user2 = createUserResponseDTO(2L, "Maria", "González");

        when(userQueryService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        List<UserResponseDTO> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("Juan", users.get(0).getNombres());
        assertEquals("Maria", users.get(1).getNombres());

        verify(userQueryService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById_Success() {
        UserResponseDTO user = createUserResponseDTO(1L, "Carlos", "Ramírez");

        when(userQueryService.getUserById(1L)).thenReturn(user);

        UserResponseDTO response = userService.getUserById(1L);

        assertNotNull(response);
        assertEquals("Carlos", response.getNombres());

        verify(userQueryService, times(1)).getUserById(1L);
    }

    @Test
    void testCreateUser_Success() {
        UserRequestDTO dto = createUserRequestDTO();
        UserResponseDTO responseDTO = createUserResponseDTO(1L, "Juan", "Pérez");

        when(userCommandService.createUser(dto)).thenReturn(responseDTO);

        UserResponseDTO response = userService.createUser(dto);

        assertNotNull(response);
        assertEquals("Juan", response.getNombres());

        verify(userCommandService, times(1)).createUser(dto);
    }

    @Test
    void testUpdateUser_Success() {
        UserRequestDTO dto = createUserRequestDTO();
        dto.setNombres("Carlos");

        UserResponseDTO updatedResponse = createUserResponseDTO(1L, "Carlos", "Pérez");

        when(userCommandService.updateUser(1L, dto)).thenReturn(updatedResponse);

        UserResponseDTO response = userService.updateUser(1L, dto);

        assertNotNull(response);
        assertEquals("Carlos", response.getNombres());

        verify(userCommandService, times(1)).updateUser(1L, dto);
    }

    @Test
    void testDeleteUser_Success() {
        doNothing().when(userCommandService).deleteUser(1L);

        assertDoesNotThrow(() -> userService.deleteUser(1L));

        verify(userCommandService, times(1)).deleteUser(1L);
    }
}
