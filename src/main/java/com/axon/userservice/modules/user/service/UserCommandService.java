package com.axon.userservice.modules.user.service;

import com.axon.userservice.modules.user.dto.UserRequestDTO;
import com.axon.userservice.modules.user.dto.UserResponseDTO;
import com.axon.userservice.modules.user.mapper.UserMapper;
import com.axon.userservice.modules.user.model.entity.UserEntity;
import com.axon.userservice.modules.user.repository.IUserRepository;
import com.axon.userservice.modules.user.validators.UserValidator;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserCommandService implements IUserCommandService {

    private static final Logger logger = LoggerFactory.getLogger(UserCommandService.class);

    private final IUserRepository userRepository;
    private final UserValidator userValidator;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserCommandService(IUserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        logger.info("Iniciando creación de usuario con correo: {}", dto.getCorreoElectronico());

        userValidator.validateNewUser(dto);
        UserEntity user = UserMapper.toEntity(dto);
        user.setContrasena(passwordEncoder.encode(dto.getContrasena()));

        UserEntity savedUser = userRepository.save(user);

        logger.info("Usuario creado con éxito, ID: {}", savedUser.getId());
        return UserMapper.toResponseDTO(savedUser);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        logger.info("Iniciando actualización de usuario con ID: {}", id);

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Error: Usuario con ID {} no encontrado", id);
                    return new RuntimeException("Usuario no encontrado");
                });

        user.setNombres(dto.getNombres());
        user.setApellidos(dto.getApellidos());
        user.setDv(dto.getDv());
        user.setFechaNacimiento(dto.getFechaNacimiento());
        user.setCorreoElectronico(dto.getCorreoElectronico());

        if (dto.getContrasena() != null && !dto.getContrasena().isEmpty()) {
            logger.info("Actualizando contraseña del usuario con ID: {}", id);
            user.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        }

        UserEntity updatedUser = userRepository.save(user);
        logger.info("Usuario actualizado con éxito, ID: {}", updatedUser.getId());

        return UserMapper.toResponseDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        logger.info("Iniciando eliminación de usuario con ID: {}", id);

        if (!userRepository.existsById(id)) {
            logger.error("Error: Usuario con ID {} no encontrado para eliminación", id);
            throw new RuntimeException("Usuario no encontrado");
        }

        userRepository.deleteById(id);
        logger.info("Usuario eliminado con éxito, ID: {}", id);
    }
}
