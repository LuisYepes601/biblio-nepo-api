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
public class TipoIdentificacionReq {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Rgistro civil")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+$",
            message = "El nombre solo puede contener letras y espacios")
    @Size(min = 2, max = 100, message = "El nombre del tipo de identificación debe contener entre 200 y 100 caracteres")
    @NotBlank(message = "El nombre no puede quedar vacio")
    private String nombre;

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(max = 200, message = "La descripcion del tipo de identificación no debe de superar los 200 caracteres")
    private String descripcion;

    public TipoIdentificacionReq(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public TipoIdentificacionReq() {
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
