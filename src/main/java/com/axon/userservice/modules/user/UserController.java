package com.axon.userservice.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public List<UserEntity> getAllUsers() { return service.getAllUsers(); }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return service.getUserById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) { return service.createUser(user); }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) { return service.updateUser(id, user); }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) { service.deleteUser(id); }
}
