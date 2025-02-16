package com.axon.userservice.modules.user.service;

import com.axon.userservice.modules.user.dto.UserResponseDTO;

import java.util.List;

public interface IUserQueryService {
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
}
