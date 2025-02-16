package com.axon.userservice.modules.user;

import com.axon.userservice.modules.user.dto.UserRequestDTO;
import com.axon.userservice.modules.user.dto.UserResponseDTO;
import com.axon.userservice.modules.user.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User API V1", description = "Operaciones para gestión de usuarios - Versión 1.2.0")
public class UserController {

    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user) {
        return ResponseEntity.status(201).body(service.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(service.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
