/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request.Usuarios;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

/**
 *
 * @author luis
 */
public class UsuarioBasicoDtoReq {

    @Schema(
            description = "Primer nombre de la persona",
            example = "Luis",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "El primer nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El primer nombre debe contener entre 2 y 100 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+(?:[ '-][a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+)*$",
            message = "El primer nombre solo puede contener letras, espacios, apóstrofes y guiones"
    )
    private String nombre;

    @Schema(
            description = "Segundo nombre de la persona",
            example = "Fernando",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(min = 2, max = 100, message = "El segundo nombre debe contener entre 2 y 100 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+(?:[ '-][a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+)*$",
            message = "El segundo nombre solo puede contener letras, espacios, apóstrofes y guiones"
    )
    private String segundoNombre;

    @Schema(
            description = "Primer apellido de la persona",
            example = "Yepes",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "El primer apellido no puede estar vacío")
    @Size(min = 2, max = 100, message = "El primer apellido debe contener entre 2 y 100 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+(?:[ '-][a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+)*$",
            message = "El primer apellido solo puede contener letras, espacios, apóstrofes y guiones"
    )
    private String primerApellido;

    @Schema(
            description = "Segundo apellido de la persona",
            example = "Meléndez",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Size(min = 2, max = 100, message = "El segundo apellido debe contener entre 2 y 100 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+(?:[ '-][a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+)*$",
            message = "El segundo apellido solo puede contener letras, espacios, apóstrofes y guiones"
    )
    private String segundoApellido;

    @Schema(
            description = "Fecha de nacimiento",
            example = "2000-05-15",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @Past(message = "La fecha de nacimiento debe ser una fecha anterior a la actual")
    private LocalDate fechaNacimiento;

    @Schema(
            description = "Número de identificación",
            example = "1234567890",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "El número de identificación no puede estar vacío")
    @Size(min = 5, max = 20, message = "El número de identificación debe contener entre 5 y 20 caracteres")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "El número de identificación solo puede contener números"
    )
    private String numeroIdentificacion;

    @Schema(
            description = "Identificador del tipo de identificación",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "El tipo de identificación es obligatorio")
    @Positive(message = "El ID del tipo de identificación debe ser mayor que cero")
    private Integer id_tipo_identificacion;

    @Schema(
            description = "Identificador del rol",
            example = "2",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "El rol es obligatorio")
    @Positive(message = "El ID del rol debe ser mayor que cero")
    private Integer id_rol;

    @Schema(
            description = "Correo electrónico del usuario",
            example = "luis.yepes@gmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El correo electrónico no tiene un formato válido")
    @Size(max = 150, message = "El correo electrónico no puede superar los 150 caracteres")
    private String email;

    @Schema(
            description = "Contraseña del usuario",
            example = "Luis@2026Password",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(
            min = 8,
            max = 100,
            message = "La contraseña debe contener entre 8 y 100 caracteres"
    )
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial"
    )
    private String password;

    public UsuarioBasicoDtoReq(String nombre, String segundoNombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, String numeroIdentificacion, Integer id_tipo_identificacion, Integer id_rol, String email, String password) {
        this.nombre = nombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroIdentificacion = numeroIdentificacion;
        this.id_tipo_identificacion = id_tipo_identificacion;
        this.id_rol = id_rol;
        this.email = email;
        this.password = password;
    }
    
    

    public UsuarioBasicoDtoReq() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Integer getId_tipo_identificacion() {
        return id_tipo_identificacion;
    }

    public void setId_tipo_identificacion(Integer id_tipo_identificacion) {
        this.id_tipo_identificacion = id_tipo_identificacion;
    }

    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
