package com.axon.userservice.modules.user.repository;

import com.axon.userservice.modules.user.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Long id);
    boolean existsByCorreoElectronico(String correoElectronico);
    boolean existsByRut(Long rut);
    boolean existsById(Long id);
    UserEntity save(UserEntity user);
    void deleteById(Long id);
}
