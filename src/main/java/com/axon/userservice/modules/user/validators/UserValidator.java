package com.axon.userservice.modules.user.validators;

import com.axon.userservice.modules.user.dto.UserRequestDTO;
import com.axon.userservice.modules.user.model.entity.UserEntity;
import com.axon.userservice.modules.user.repository.IUserRepository;
import com.axon.userservice.utils.RutUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserValidator {

    private final IUserRepository userRepository;
    private final ValidatorPassword validatorPassword;
    private final ValidatorAge validatorAge;

    public UserValidator(IUserRepository repository, ValidatorPassword validatorPassword, ValidatorAge validatorAge) {
        this.userRepository = repository;
        this.validatorPassword = validatorPassword;
        this.validatorAge = validatorAge;
    }

    public void validateNewUser(UserRequestDTO dto) {
        validateUniqueCorreoElectronico(dto.getCorreoElectronico());
        validateUniqueRut(dto.getRut());
        validateRut(dto.getRut(), dto.getDv());

        if (!validatorPassword.isValid(dto.getContrasena())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contraseña no cumple con los requisitos de seguridad.");
        }
    }

    public void validateUpdateUser(Long id, UserRequestDTO dto) {
        UserEntity user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (!user.getCorreoElectronico().equals(dto.getCorreoElectronico())) {
            validateUniqueCorreoElectronico(dto.getCorreoElectronico());
        }

        if (!user.getRut().equals(dto.getRut())) {
            validateUniqueRut(dto.getRut());
        }

        validateRut(dto.getRut(), dto.getDv());

        if (dto.getContrasena() != null && !dto.getContrasena().isEmpty() &&
                !validatorPassword.isValid(dto.getContrasena())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La nueva contraseña no cumple con los requisitos de seguridad.");
        }
    }

    public void validateDeleteUser(Long id) {
        if (this.userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado.");
        }
    }

    private void validateUniqueCorreoElectronico(String correo) {
        if (this.userRepository.existsByCorreoElectronico(correo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo electrónico ya está en uso.");
        }
    }

    private void validateUniqueRut(Long rut) {
        if (this.userRepository.existsByRut(rut)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El RUT ya está registrado.");
        }
    }

    private void validateRut(Long rut, String dv) {
        if (!RutUtils.validateRut(rut, dv)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El RUT y el dígito verificador no coinciden.");
        }
    }
}
