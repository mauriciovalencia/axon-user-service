package com.axon.userservice.modules.user.service;

import com.axon.userservice.modules.user.dto.UserRequestDTO;
import com.axon.userservice.modules.user.dto.UserResponseDTO;

public interface IUserCommandService {
    UserResponseDTO createUser(UserRequestDTO user);
    UserResponseDTO updateUser(Long id, UserRequestDTO user);
    void deleteUser(Long id);
}
