/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author luis
 */
public class RolDtoAdminReq {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "ADMIN"
    )
    @Size(min = 2, max = 100, message = "El rol debe de tener entre 2 y 100 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$",
            message = "Solo se permiten letras y espacios")
    @NotBlank(message = "El nombre del rol no puede quedar vacio.")
    private String nombre;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(max = 200, message = "La descripción no puede superar los 200 caracteres")
    private String descripcion;

    public RolDtoAdminReq(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public RolDtoAdminReq() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
