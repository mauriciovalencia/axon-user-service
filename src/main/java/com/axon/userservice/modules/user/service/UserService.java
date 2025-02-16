package com.axon.userservice.modules.user.service;

import com.axon.userservice.modules.user.dto.UserRequestDTO;
import com.axon.userservice.modules.user.dto.UserResponseDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserQueryService userQueryService;
    private final IUserCommandService userCommandService;

    public UserService(IUserQueryService userQueryService, IUserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userQueryService.getAllUsers();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return userQueryService.getUserById(id);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO user) {
        return userCommandService.createUser(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO user) {
        return userCommandService.updateUser(id, user);
    }

    @Override
    public void deleteUser(Long id) {
        userCommandService.deleteUser(id);
    }
}
