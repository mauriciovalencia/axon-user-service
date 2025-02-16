package com.axon.userservice.modules.user.service;

import com.axon.userservice.modules.user.dto.UserRequestDTO;
import com.axon.userservice.modules.user.dto.UserResponseDTO;

import java.util.List;

public interface IUserService {
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO createUser(UserRequestDTO user);
    UserResponseDTO updateUser(Long id, UserRequestDTO user);
    void deleteUser(Long id);
}
