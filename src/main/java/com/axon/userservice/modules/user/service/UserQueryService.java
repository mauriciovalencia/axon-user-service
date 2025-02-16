package com.axon.userservice.modules.user.service;

import com.axon.userservice.modules.user.dto.UserResponseDTO;
import com.axon.userservice.modules.user.mapper.UserMapper;
import com.axon.userservice.modules.user.model.entity.UserEntity;
import com.axon.userservice.modules.user.repository.IUserRepository;
import com.axon.userservice.modules.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserQueryService implements IUserQueryService {

    private final IUserRepository repository;

    public UserQueryService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll().stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return UserMapper.toResponseDTO(user);
    }
}
