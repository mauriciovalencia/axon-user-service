package com.axon.userservice.modules.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserEntity> getAllUsers() {
        return repository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return repository.findById(id);
    }

    public UserEntity createUser(UserEntity user) {
        if (user.getId() != null && user.getId() == 0) {
            user.setId(null);
        }
        return repository.save(user);
    }

    @Transactional
    public UserEntity updateUser(Long id, UserEntity userDetails) {
        return repository.findById(id).map(user -> {
            user.setNombres(userDetails.getNombres());
            user.setApellidos(userDetails.getApellidos());
            user.setRut(userDetails.getRut());
            user.setDv(userDetails.getDv());
            user.setFechaNacimiento(userDetails.getFechaNacimiento());
            user.setCorreoElectronico(userDetails.getCorreoElectronico());
            user.setContrasena(userDetails.getContrasena());
            return repository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
