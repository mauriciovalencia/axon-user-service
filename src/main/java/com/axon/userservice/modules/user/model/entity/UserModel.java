package com.axon.userservice.modules.user.model.entity;

import java.time.LocalDate;
import java.util.Date;
public interface UserModel {
    Long id = null;
    String nombres = null;
    String apellidos = null;
    Long rut = null;
    String dv = null;
    LocalDate fechaNacimiento = null;
    String correoElectronico = null;
    String contrasena = null;
    Date createdAt = null;
    Date updatedAt = null;
}
