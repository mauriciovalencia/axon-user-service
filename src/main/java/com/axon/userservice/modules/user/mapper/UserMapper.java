package com.axon.userservice.modules.user.mapper;

import com.axon.userservice.modules.user.model.entity.UserEntity;
import com.axon.userservice.modules.user.dto.UserRequestDTO;
import com.axon.userservice.modules.user.dto.UserResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static UserEntity toEntity(UserRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO no puede ser nulo");
        }

        UserEntity user = new UserEntity();
        user.setNombres(dto.getNombres());
        user.setApellidos(dto.getApellidos());
        user.setRut(dto.getRut());
        user.setDv(dto.getDv());
        user.setFechaNacimiento(dto.getFechaNacimiento());
        user.setCorreoElectronico(dto.getCorreoElectronico());
        user.setContrasena(passwordEncoder.encode(dto.getContrasena()));

        return user;
    }

    public static UserResponseDTO toResponseDTO(UserEntity user) {
        if (user == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }

        return new UserResponseDTO(
                user.getId(),
                user.getNombres(),
                user.getApellidos(),
                user.getRut(),
                user.getDv(),
                user.getFechaNacimiento(),
                user.getCorreoElectronico()
        );
    }
}
