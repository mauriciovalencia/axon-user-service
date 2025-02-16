package com.axon.userservice.modules.user.dto;

import java.time.LocalDate;

public class UserResponseDTO {
    private Long id;
    private String nombres;
    private String apellidos;
    private Long rut;
    private String dv;
    private LocalDate fechaNacimiento;
    private String correoElectronico;

    public UserResponseDTO() {}

    public UserResponseDTO(Long id, String nombres, String apellidos, Long rut, String dv, LocalDate fechaNacimiento, String correoElectronico) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rut = rut;
        this.dv = dv;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public Long getRut() { return rut; }
    public void setRut(Long rut) { this.rut = rut; }

    public String getDv() { return dv; }
    public void setDv(String dv) { this.dv = dv; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", rut=" + rut +
                ", dv='" + dv + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}
