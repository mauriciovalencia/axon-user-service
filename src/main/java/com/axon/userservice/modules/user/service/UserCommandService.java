package com.axon.userservice.modules.user.service;

import com.axon.userservice.modules.user.dto.UserRequestDTO;
import com.axon.userservice.modules.user.dto.UserResponseDTO;
import com.axon.userservice.modules.user.mapper.UserMapper;
import com.axon.userservice.modules.user.model.entity.UserEntity;
import com.axon.userservice.modules.user.repository.IUserRepository;
import com.axon.userservice.modules.user.repository.UserRepository;
import com.axon.userservice.modules.user.validators.UserValidator;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCommandService implements IUserCommandService {

    private final IUserRepository userRepository;
    private final UserValidator userValidator;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserCommandService(IUserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        userValidator.validateNewUser(dto);
        UserEntity user = UserMapper.toEntity(dto);
        user.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        UserEntity savedUser = userRepository.save(user);
        return UserMapper.toResponseDTO(savedUser);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setNombres(dto.getNombres());
        user.setApellidos(dto.getApellidos());
        user.setDv(dto.getDv());
        user.setFechaNacimiento(dto.getFechaNacimiento());
        user.setCorreoElectronico(dto.getCorreoElectronico());

        if (dto.getContrasena() != null && !dto.getContrasena().isEmpty()) {
            user.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        }

        UserEntity updatedUser = userRepository.save(user);
        return UserMapper.toResponseDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }
}
