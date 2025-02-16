package com.axon.userservice.modules.user.dto;

import com.axon.userservice.modules.user.validators.ValidAge;
import com.axon.userservice.modules.user.validators.ValidRut;
import com.axon.userservice.modules.user.validators.ValidRutDv;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserRequestDTO {

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$", message = "El nombre solo puede contener letras y espacios")
    @NotBlank(message = "Los nombres son obligatorios")
    private String nombres;

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$", message = "El apellido solo puede contener letras y espacios")
    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @NotNull(message = "El RUT es obligatorio")
    @ValidRut
    private Long rut;

    @NotNull(message = "El dígito verificador es obligatorio")
    @ValidRutDv
    private String dv;

    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @ValidAge
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @Email(message = "El correo electrónico debe ser válido")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Formato de correo inválido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    private String correoElectronico;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "La contraseña debe contener al menos una mayúscula, un número y un carácter especial")
    private String contrasena;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getRut() {
        return rut;
    }

    public void setRut(Long rut) {
        this.rut = rut;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
