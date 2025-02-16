package com.axon.userservice.modules.user;

import com.axon.userservice.modules.user.dto.UserRequestDTO;
import com.axon.userservice.modules.user.dto.UserResponseDTO;
import com.axon.userservice.modules.user.mapper.UserMapper;
import com.axon.userservice.modules.user.model.entity.UserEntity;
import com.axon.userservice.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll().stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return UserMapper.toResponseDTO(user);
    }

    public UserResponseDTO createUser(UserRequestDTO dto) {
        UserEntity user = UserMapper.toEntity(dto);
        UserEntity savedUser = repository.save(user);
        return UserMapper.toResponseDTO(savedUser);
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setNombres(dto.getNombres());
        user.setApellidos(dto.getApellidos());
        user.setRut(dto.getRut());
        user.setDv(dto.getDv());
        user.setFechaNacimiento(dto.getFechaNacimiento());
        user.setCorreoElectronico(dto.getCorreoElectronico());
        user.setContrasena(dto.getContrasena());

        UserEntity updatedUser = repository.save(user);
        return UserMapper.toResponseDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
