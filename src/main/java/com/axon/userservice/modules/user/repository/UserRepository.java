package com.axon.userservice.modules.user.repository;

import com.axon.userservice.modules.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, IUserRepository {
    boolean existsByCorreoElectronico(String correoElectronico);
    boolean existsByRut(Long rut);
    Optional<UserEntity> findByCorreoElectronico(String correoElectronico);
}
