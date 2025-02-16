package com.axon.userservice.modules.user.service;

import com.axon.userservice.modules.user.dto.UserResponseDTO;
import com.axon.userservice.modules.user.mapper.UserMapper;
import com.axon.userservice.modules.user.model.entity.UserEntity;
import com.axon.userservice.modules.user.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserQueryService implements IUserQueryService {

    private static final Logger logger = LoggerFactory.getLogger(UserQueryService.class);

    private final IUserRepository repository;

    public UserQueryService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        logger.info("Consultando todos los usuarios...");

        List<UserResponseDTO> users = repository.findAll().stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());

        logger.info("Se encontraron {} usuarios en la base de datos", users.size());
        return users;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        logger.info("Consultando usuario con ID: {}", id);

        UserEntity user = repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Error: Usuario con ID {} no encontrado", id);
                    return new RuntimeException("Usuario no encontrado");
                });

        logger.info("Usuario con ID {} encontrado: {}", id, user.getCorreoElectronico());
        return UserMapper.toResponseDTO(user);
    }
}
